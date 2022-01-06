package com.pp.helpdesk.api;

import com.pp.helpdesk.model.ticket.Ticket;
import com.pp.helpdesk.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketApi {
    private TicketService ticketService;

    public TicketApi(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/add")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.add(ticket);
    }

    @GetMapping("/getAll")
    public List<Ticket> getAllTicket() {
        return ticketService.getAll();
    }

    @GetMapping("/getByClient")
    public List<Ticket> getByClient() {
        return ticketService.getByClient();
    }

    @GetMapping("/getByServiceman")
    public List<Ticket> getByServiceman() {
        return ticketService.getByServiceman();
    }

    @PutMapping("/close/{id}")
    public void close(@PathVariable Long id) {
        ticketService.close(id);
    }

    @PutMapping("/assign/{id}")
    public void assignServiceman(@PathVariable Long id) {
        ticketService.assignServiceman(id);
    }

}
