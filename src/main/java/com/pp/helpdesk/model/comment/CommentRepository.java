package com.pp.helpdesk.model.comment;

import com.pp.helpdesk.model.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTicket(Ticket ticket);
}
