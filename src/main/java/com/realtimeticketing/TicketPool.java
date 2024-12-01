package com.realtimeticketing;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private final int maxCapacity;
    private final List<Ticket> tickets;
    private int ticketCounter = 0;
    private final Logger logger = Logger.getInstance();

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.tickets = new ArrayList<>();
    }

    public synchronized void addTicket()   {
        while(tickets.size() == maxCapacity) {
            try{
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000); // Add a delay of 1 second for ticket release
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        Ticket ticket = new Ticket(++ticketCounter);
        tickets.add(ticket);
        logger.log("Ticket Added: " + ticket.getId());
        notifyAll();
    }

    public synchronized Ticket removeTicket()   {
        while(tickets.isEmpty()){
            try{
                wait();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        Ticket ticket = tickets.remove(0);
        logger.log("Ticket Removed: " + ticket.getId());
        notifyAll();
        return ticket;
    }

    public synchronized int getTicketCounter() {
        return tickets.size();
    }
}
