package com.realtimeticketing;

public class Vendor implements Runnable{
    private final TicketPool ticketPool;
    private final int releaseRate;
    private final String vendorId;
    private final Logger logger = Logger.getInstance();

    public Vendor(String vendorId,TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                ticketPool.addTicket();
                logger.log("Vendor " + vendorId + " released ticket");
                Thread.sleep(1000/releaseRate);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

    }
}
