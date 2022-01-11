package com.pp.helpdesk.service;

import com.pp.helpdesk.model.comment.Comment;
import com.pp.helpdesk.model.comment.CommentRepository;
import com.pp.helpdesk.model.ticket.Ticket;
import com.pp.helpdesk.model.ticket.TicketRepository;
import com.pp.helpdesk.model.user.User;
import com.pp.helpdesk.model.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public Comment add(Comment comment, Long ticketId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = userRepository.findByUserName(userDetails.getUsername()).orElseThrow(RuntimeException::new);

        comment.setUser(loggedUser);
        Ticket ticketFromDb = ticketRepository.findByTicketId(ticketId);
        comment.setTicket(ticketFromDb);
        return commentRepository.save(comment);
    }

    public List<Comment> getByTicket(Long ticketId) {
        Ticket ticketFromDb = ticketRepository.findByTicketId(ticketId);

        return commentRepository.findByTicket(ticketFromDb);
    }
}
