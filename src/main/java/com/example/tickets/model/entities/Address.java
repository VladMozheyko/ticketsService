package com.example.tickets.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "adrees")
public class Address {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String street; //Поле не может быть null
    private String zipCode; //Длина строки не должна быть больше 29, Поле не может быть null

    public Address() {


    }

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}

