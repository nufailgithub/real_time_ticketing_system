package com.realtimeticketing;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketPool {
    private final BlockingQueue<Ticket> tickets;
    private final AtomicInteger numTickets = new AtomicInteger(0);
    private final int maxTickets;
    private final Logger logger = Logger.getInstance();


    public TicketPool( int maxTickets) {
        this.tickets = new LinkedBlockingQueue<>(maxTickets);
        this.maxTickets = maxTickets;
    }

    public void addTickets() throws InterruptedException {
        Ticket ticket = new Ticket(numTickets.incrementAndGet());
        tickets.put(ticket);
        logger.log("Ticket added to the pool :"+ ticket.getId());

    }

    public Ticket removeTicket() throws InterruptedException {
        Ticket ticket = tickets.take();
        logger.log("Ticket removed from the pool :" + ticket.getId());
        return ticket;
    }
}
