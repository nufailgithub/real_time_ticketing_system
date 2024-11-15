package com.realtimeticketing;

import java.util.Scanner;

public class Configuration {
    private  static Configuration config_instance;

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    private Configuration () {}

    public static synchronized Configuration getInstance () {
        if (config_instance == null) {
            config_instance = new Configuration();
        }
        return config_instance;
    }

    public void loadConfiguration() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
        
        ============= System Configuration =============
        
        """);

        try {
            System.out.print("Enter total number of tickets: ");
            totalTickets = validatePositiveInput(scanner.nextLine());

            System.out.print("Enter ticket release rate (tickets per second): ");
            ticketReleaseRate = validatePositiveInput(scanner.nextLine());

            System.out.print("Enter customer retrieval rate (tickets per second): ");
            customerRetrievalRate = validatePositiveInput(scanner.nextLine());

            System.out.print("Enter maximum ticket capacity(A customer can buy in at a time): ");
            maxTicketCapacity = validatePositiveInput(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.err.println("Configuration error: " + e.getMessage());
            System.exit(1);
        }
    }

    private int validatePositiveInput(String input) {
        try {
            int value = Integer.parseInt(input);
            if (value <= 0) {
                throw new IllegalArgumentException("Value must be positive");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        }
    }

    // Getters
    public int getTotalTickets() { return totalTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }


}
