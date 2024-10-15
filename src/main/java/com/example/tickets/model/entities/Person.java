package com.example.tickets.model.entities;


import com.example.tickets.UtilRepository;
import com.example.tickets.model.enums.Role;
import jakarta.persistence.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    @OneToOne
    private Location location; //Поле может быть null
    private int height; //Значение поля должно быть больше 0
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    private java.awt.Color color;

    public java.awt.Color getColor() {
        return color;
    }

    public void setColor() {
        Random random = new Random();


       color = new java.awt.Color(random.nextInt(256), random.nextInt(256),
                random.nextInt(256));
    }
//    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
//    private List<PersonTicketHistory> ticketHistories;  // История изменений билетов


    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<PersonTicketHistory> personTicketHistory; // Связь с Ticket

    @Column(name = "password", nullable = false, unique = true)
    private String password;
    @Enumerated(EnumType.STRING)  // Сохранение значения Enum как строки
    private Role role;

    public Person(int id, Color eyeColor, Color hairColor, Location location, int height, List<PersonTicketHistory> personTicketHistory) {
        this.id = id;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
        this.height = height;
        this.personTicketHistory = personTicketHistory;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = UtilRepository.hashPassword(password); // Хешируем пароль при установке
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", location=" + location +
                ", height=" + height +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}' + "\n";
    }
}
