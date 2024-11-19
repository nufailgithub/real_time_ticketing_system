package com.realtimeticketing.realtimetickeing.controller;
import com.realtimeticketing.realtimetickeing.model.Ticket;
import com.realtimeticketing.realtimetickeing.service.TicketPoolService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {
    private final TicketPoolService ticketPoolService;

    public TicketController(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }

    @PostMapping("/vendor/{vendorId}")
    public void addTickets(@PathVariable Long vendorId, @RequestParam int count) {
        ticketPoolService.addTickets(count, vendorId);
    }

    @PostMapping("/purchase/{customerId}")
    public Ticket purchaseTicket(@PathVariable Long customerId) {
        return ticketPoolService.purchaseTicket(customerId);
    }
}
