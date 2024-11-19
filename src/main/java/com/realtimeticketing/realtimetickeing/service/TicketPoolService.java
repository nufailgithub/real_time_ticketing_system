package com.realtimeticketing.realtimetickeing.service;

import com.realtimeticketing.realtimetickeing.model.Ticket;
import com.realtimeticketing.realtimetickeing.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketPoolService {
    private final TicketRepository ticketRepository;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPoolService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public synchronized void addTickets(int count, Long vendorId) {
        lock.lock();
        try {
            for (int i = 0; i < count; i++) {
                Ticket ticket = new Ticket();
                ticket.setStatus("AVAILABLE");
                ticket.setVendorId(vendorId);
                ticketRepository.save(ticket);
            }
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public synchronized Ticket purchaseTicket(Long customerId) {
        lock.lock();
        try {
            List<Ticket> availableTickets = ticketRepository.findAvailableTickets();
            if (!availableTickets.isEmpty()) {
                Ticket ticket = availableTickets.get(0);
                ticket.setStatus("SOLD");
                ticket.setCustomerId(customerId);
                return ticketRepository.save(ticket);
            }
            return null;
        } finally {
            lock.unlock();
        }
    }
}
