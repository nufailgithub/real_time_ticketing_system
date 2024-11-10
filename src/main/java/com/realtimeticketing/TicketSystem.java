package com.realtimeticketing;

public class TicketSystem {



    public static void main(String[] args) {
        Configuration config = Configuration.getInstance();
        config.loadConfiguration();


        System.out.print("Hello and welcome!");

    }
}