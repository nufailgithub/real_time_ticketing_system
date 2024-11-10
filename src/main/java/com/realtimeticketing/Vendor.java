package com.realtimeticketing;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final String vendorId;
    private final Logger logger = Logger.getInstance();
    private final int releaseRate;

    public Vendor(String vendorId, TicketPool ticketPool, int releaseRate) {
        this.vendorId = vendorId;
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                ticketPool.addTickets();
                logger.log("Vendor: " + vendorId + "released the tickets to the pool");
                Thread.sleep(1000/releaseRate);
            }

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            logger.log("Vendor" + vendorId +"stoped");
        }

    }
}
