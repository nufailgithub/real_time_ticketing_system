package com.realtimeticketing;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final String customerId;
    private final int retrievalRate;
    private final Logger logger = Logger.getInstance();


    public Customer(String customerId,TicketPool ticketPool, int retrievalRate ) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
        this.customerId = customerId;
    }

    @Override
    public void run() {
            try {
                while(!Thread.currentThread().isInterrupted()){
                    Ticket ticket = ticketPool.removeTicket();
                    logger.log("Customer " + customerId + " purchased " + ticket.getId());
                    Thread.sleep(1000/retrievalRate);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

