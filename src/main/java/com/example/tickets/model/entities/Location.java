package com.example.tickets.model.entities;

import jakarta.persistence.*;
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long x;
    private double y;
    private String name; //Строка не может быть пустой, Поле может быть null

    public Location(int id, long x, double y, String name) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Location() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
