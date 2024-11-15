package com.realtimeticketing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main application class
public class TicketSystem {
    private final TicketPool ticketPool;
    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();
    private final Logger logger = Logger.getInstance();
    private boolean running = false;

    public TicketSystem() {
        Configuration config = Configuration.getInstance();
        config.loadConfiguration();

        this.ticketPool = new TicketPool(config.getMaxTicketCapacity());

        // Create vendor threads
        for (int i = 0; i < 3; i++) {
            Thread vendorThread = new Thread(
                    new Vendor("V" + (i + 1), ticketPool, config.getTicketReleaseRate())
            );
            vendorThreads.add(vendorThread);
        }

        // Create customer threads
        for (int i = 0; i < 5; i++) {
            Thread customerThread = new Thread(
                    new Customer("C" + (i + 1), ticketPool, config.getCustomerRetrievalRate())
            );
            customerThreads.add(customerThread);
        }
    }

    public void start() {
        logger.log("Starting Ticket System");
        running = true;

        // Start all threads
        vendorThreads.forEach(Thread::start);
        customerThreads.forEach(Thread::start);

        // Monitor system for total tickets sold
        new Thread(() -> {
            try {
                int totalTickets = Configuration.getInstance().getTotalTickets();
                while (running) {
                    int available = ticketPool.getAvailableTickets();
                    logger.log("========== Current tickets in pool ========== " + available);
                    Thread.sleep(1000);

                    if (available >= totalTickets) {
                        shutdown();
                        break;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public void shutdown() {
        logger.log("Shutting down Ticket System");
        running = false;

        // Interrupt all threads
        vendorThreads.forEach(Thread::interrupt);
        customerThreads.forEach(Thread::interrupt);

        // Wait for all threads to complete
        try {
            for (Thread t : vendorThreads) t.join();
            for (Thread t : customerThreads) t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log("Shutdown interrupted");
        }
        logger.log("System shutdown complete");
    }

    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Enter 'start' to begin the Ticket System, 'stop' to shut it down, or 'exit' to quit:");
            String command = scanner.nextLine();

            switch (command) {
                case "start":
                    if (!system.running) {
                        system.start();
                    } else {
                        System.out.println("The Ticket System is already running.");
                    }
                    break;
                case "stop":
                    if (system.running) {
                        system.shutdown();
                    } else {
                        System.out.println("The Ticket System is not running.");
                    }
                    break;
                case "exit":
                    exit = true;
                    break;
                    
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }

        System.out.println("Exiting the Ticket System.");
        scanner.close();
    }
}