package com.realtimeticketing.realtimetickeing.repository;

import com.realtimeticketing.realtimetickeing.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.status = 'AVAILABLE'")
    List<Ticket> findAvailableTickets();
}
