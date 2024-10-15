package com.example.tickets.model.entities;

import com.example.tickets.model.enums.VenueType;
import jakarta.persistence.*;

import javax.persistence.Table;

@Entity
@Table(name = "venue")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long capacity; //Значение поля должно быть больше 0
    private VenueType type; //Поле не может быть null
    @OneToOne
    private Address address; //Поле может быть null

    public Venue(Integer id, String name, long capacity, VenueType type, Address address) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.address = address;
    }
    public Venue() {

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

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public VenueType getType() {
        return type;
    }

    public void setType(VenueType type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

