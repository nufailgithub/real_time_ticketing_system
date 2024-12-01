package com.realtimeticketing;

import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int releaseRate;
    private int retrievalRate;
    private int maxTickets;

    public int getMaxTickets() {
        return maxTickets;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getReleaseRate() {
        return releaseRate;
    }

    public void setReleaseRate(int releaseRate) {
        this.releaseRate = releaseRate;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public void setRetrievalRate(int retrievalRate) {
        this.retrievalRate = retrievalRate;
    }

    public void setMaxTickets(int maxTickets) {
        this.maxTickets = maxTickets;
    }

    public void configure (){
        Scanner scan = new Scanner(System.in);

        System.out.println(
                """
               ------------------
               CLI PART TICKETING
               ------------------
                        """
        );

        while(true)
        {
           try{
               System.out.print("Enter number of tickets: ");
               totalTickets =validatePositiveInput(scan.next());
               System.out.print("Enter release rate: ");
               releaseRate = validatePositiveInput(scan.next());
               System.out.print("Enter retrieval rate: ");
               retrievalRate = validatePositiveInput(scan.next());
               System.out.print("Enter max tickets: ");
               maxTickets = validatePositiveInput(scan.next());

               System.out.println("SYSTEM CONFIGURATION COMPLETED SUCCESSFULLY");
               break;

           }catch (Exception e){
               System.out.println(e.getMessage());
           }
        }
    }

    private int validatePositiveInput (String input){
        try{
            int value = Integer.parseInt(input);
            if (value <= 0){
                throw new IllegalArgumentException("Value must be positive and greater than zero");
            }
            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
