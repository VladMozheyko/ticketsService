package com.example.tickets.model.entities;

import com.example.tickets.model.enums.EventType;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private ZonedDateTime date; //Поле может быть null
    private long minAge;
    private int ticketsCount; //Значение поля должно быть больше 0
    private EventType eventType; //Поле может быть null

    public Event(Integer id, String name, ZonedDateTime date, long minAge, int ticketsCount, EventType eventType) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.minAge = minAge;
        this.ticketsCount = ticketsCount;
        this.eventType = eventType;
    }

    public Event() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public long getMinAge() {
        return minAge;
    }

    public void setMinAge(long minAge) {
        this.minAge = minAge;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
