package com.example.tickets.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Float x; //Максимальное значение поля: 755, Поле не может быть null
    private int y; //Максимальное значение поля: 685


    public Coordinates(int id, Float x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Coordinates() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
