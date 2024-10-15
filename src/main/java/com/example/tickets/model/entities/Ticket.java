package com.example.tickets.model.entities;

import com.example.tickets.model.enums.TicketType;
import jakarta.persistence.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    @OneToOne
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @ManyToOne
    @JoinColumn(name = "person_id")   // Создаст внешний ключ в таблице ticket
    private Person person;            // Поле может быть null

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<PersonTicketHistory> ticketHistories;  // История изменений
    @OneToOne
    private Event event; //Поле не может быть null
    private double price; //Значение поля должно быть больше 0
    private TicketType type; //Поле не может быть null
    private int discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private Integer number; //Поле может быть null, Значение поля должно быть больше 0
    @OneToOne
    private Venue venue; //Поле не может быть null

    private boolean isBooked;

    private boolean isSold;

    public Ticket(Integer id, String name, Coordinates coordinates, Date creationDate, Person person, Event event, double price, TicketType type, int discount, Integer number, Venue venue) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.person = person;
        this.event = event;
        this.price = price;
        this.type = type;
        this.discount = discount;
        this.number = number;
        this.venue = venue;
    }

    public Ticket() {

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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Person Person() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;

    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }


    public void setTicketHistories(PersonTicketHistory ticketHistory) {
        if(this.ticketHistories == null){
            this.ticketHistories = new ArrayList<PersonTicketHistory>();
        }
        ticketHistories.add(ticketHistory);
    }

    public void paint() {

        int width = 100;
        int height = 60;


        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Рисование на BufferedImage
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(person.getColor());
        g2d.fillRect(0, 0, width, height); // Заполнение фона желтым
        g2d.dispose(); // Освобождение ресурсов

        // Сохранение изображения в файл PNG
        try {
            File outputfile = new File("tickets/output"+id+".png");
            ImageIO.write(bufferedImage, "png", outputfile);
            System.out.println("Изображение сохранено как output"+id+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", person=" + person +
                ", event=" + event +
                ", price=" + price +
                ", type=" + type +
                ", discount=" + discount +
                ", number=" + number +
                ", venue=" + venue +
                ", isBooked=" + isBooked +
                ", isSoled= " + isSold +
                "ticket history" + ticketHistories.toString()+
                '}' + '\n';
    }
}
