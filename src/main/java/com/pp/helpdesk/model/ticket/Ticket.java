package com.pp.helpdesk.model.ticket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pp.helpdesk.model.application.Application;
import com.pp.helpdesk.model.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User client;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User serviceman;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Application application;
    private Date creationDate;
    private Date closingDate;
    private String status;
    private String content;
    private int priority;

    public Ticket(Application application, String content, int priority, User client) {
        this.application = application;
        this.content = content;
        this.priority = priority;
        this.client = client;
        this.creationDate = new Date();

    }

    public Ticket() {

    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getServiceman() {
        return serviceman;
    }

    public void setServiceman(User serviceman) {
        this.serviceman = serviceman;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

