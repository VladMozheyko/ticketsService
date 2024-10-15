package com.example.tickets.service;

import com.example.tickets.model.entities.Person;

import com.example.tickets.model.entities.PersonTicketHistory;
import com.example.tickets.model.entities.Ticket;
import com.example.tickets.model.enums.ActionType;
import com.example.tickets.repository.PersonRepository;
import com.example.tickets.repository.TicketRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;

public class TicketService {
    private TicketRepository ticketRepository;
    private PersonRepository personRepository;

    public TicketService(TicketRepository ticketRepository, PersonRepository personRepository) {
        this.ticketRepository = ticketRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public void createTicket(Ticket ticket, int personId){
        Person person = personRepository.get(personId);

        ticket.setPerson(person);
        PersonTicketHistory personTicketHistory = new PersonTicketHistory();
        personTicketHistory.setPerson(person);
        personTicketHistory.setActionDate(new Date());
        personTicketHistory.setTicket(ticket);
        personTicketHistory.setActionType(ActionType.CREATED);
        ticket.setTicketHistories(personTicketHistory);

        ticketRepository.create(ticket);
        ticket.paint();
    }

    public void bookTicket(int ticketId, int personId){

    }

    public ArrayList<Ticket> getAllTickets(){
        return ticketRepository.getAll();
    }


}
