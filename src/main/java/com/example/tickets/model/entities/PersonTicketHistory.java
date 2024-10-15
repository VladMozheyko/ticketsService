package com.example.tickets.model.entities;

import com.example.tickets.model.enums.ActionType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "person_ticket_history")
public class PersonTicketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;  // Ссылка на пользователя

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;  // Ссылка на билет

    @Temporal(TemporalType.TIMESTAMP)
    private Date actionDate;  // Дата и время изменения

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionType actionType;  // Тип действия (например, "created", "updated", "sold", и т.д.)

    // Конструктор, геттеры и сеттеры

    public PersonTicketHistory() {}

    public PersonTicketHistory(Person person, Ticket ticket, ActionType actionType) {
        this.person = person;
        this.ticket = ticket;
        this.actionDate = new Date(); // Текущая дата
        this.actionType = actionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

}