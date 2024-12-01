package com.realtimeticketing;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private final TicketPool ticketPool;
    private final Configuration configuration;
    private final Logger logger = Logger.getInstance();
    private ScheduledExecutorService scheduler;

    public Main(Configuration configuration) {
        this.configuration = configuration;
        this.ticketPool = new TicketPool(configuration.getMaxTickets());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Configuration configuration = new Configuration();
        configuration.configure();

        Main mainApp = new Main(configuration);

        while (true) {
            System.out.println("Enter 'START' to start the program or 'STOP' to stop the program:");
            String input = scanner.nextLine().trim().toLowerCase();

            if ("start".equals(input)) {
                mainApp.mainLogics();
            } else if ("stop".equals(input)) {
                System.out.println("SYSTEM STOPPING THE PROGRAM...");
                mainApp.shutdownScheduler();
                break;
            } else {
                System.out.println("Invalid input. Please enter 'START' or 'STOP'.");
            }
        }
    }

    private void mainLogics() {
        // Create and start vendor threads
        for (int i = 0; i < 10; i++) {
            Runnable vendor = new Vendor("V"+(i+1),ticketPool, configuration.getReleaseRate());
            Thread vendorThread = new Thread(vendor);
            vendorThread.start();
        }

        // Create and start customer threads
        for (int i = 0; i < 10; i++) {
            Runnable customer = new Customer("C"+(i+1),ticketPool, configuration.getRetrievalRate());
            Thread customerThread = new Thread(customer);
            customerThread.start();
        }

        // Schedule periodic logging of ticket pool status
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            int availableTickets = ticketPool.getTicketCounter();
            logger.log("CURRENT TICKETS IN TICKET POOL ===== " + availableTickets);
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void shutdownScheduler() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Forcing scheduler shutdown...");
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                System.out.println("Scheduler interrupted during shutdown.");
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}