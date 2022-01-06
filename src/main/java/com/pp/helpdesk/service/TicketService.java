package com.pp.helpdesk.service;

import com.pp.helpdesk.model.ticket.Ticket;
import com.pp.helpdesk.model.ticket.TicketRepository;
import com.pp.helpdesk.model.user.ERole;
import com.pp.helpdesk.model.user.User;
import com.pp.helpdesk.model.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public Ticket add(Ticket ticket) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = userRepository.findByUserName(userDetails.getUsername()).orElseThrow(RuntimeException::new);
        ticket.setClient(loggedUser);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Ticket getById(Long id) {
        return ticketRepository.findByTicketId(id);
    }

    public List<Ticket> getByClient() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = userRepository.findByUserName(userDetails.getUsername()).orElseThrow(RuntimeException::new);
        return ticketRepository.findByClient(loggedUser);
    }

    public List<Ticket> getByServiceman() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = userRepository.findByUserName(userDetails.getUsername()).orElseThrow(RuntimeException::new);
        if (loggedUser.getRoles().stream().anyMatch(role -> role.getName().equals(ERole.ROLE_ADMIN))) {
            return ticketRepository.findByServiceman(loggedUser);
        } else {
            return new ArrayList<>();
        }
    }

    public void close(Long id) {
        Ticket ticketFromDb = ticketRepository.findByTicketId(id);
        ticketFromDb.setClosingDate(new Date());
        ticketFromDb.setStatus("closed");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = userRepository.findByUserName(userDetails.getUsername()).orElseThrow(RuntimeException::new);

        if (loggedUser.getRoles().stream().anyMatch(role -> role.getName().equals(ERole.ROLE_ADMIN))) {
            ticketRepository.save(ticketFromDb);
        }
    }

    public void assignServiceman(Long id) {
        Ticket ticketFromDb = ticketRepository.findByTicketId(id);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = userRepository.findByUserName(userDetails.getUsername()).orElseThrow(RuntimeException::new);

        if (loggedUser.getRoles().stream().anyMatch(role -> role.getName().equals(ERole.ROLE_ADMIN))) {
            ticketFromDb.setServiceman(loggedUser);
            ticketRepository.save(ticketFromDb);
        }
    }
}
