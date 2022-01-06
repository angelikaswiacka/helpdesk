package com.pp.helpdesk.model.ticket;

import com.pp.helpdesk.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByTicketId(Long id);

    List<Ticket> findByClient(User client);

    List<Ticket> findByServiceman(User serviceman);

}
