package com.example.tickets;

import com.example.tickets.model.entities.Person;
import com.example.tickets.model.entities.Ticket;
import com.example.tickets.model.enums.Role;
import com.example.tickets.repository.AddressRepository;
import com.example.tickets.repository.CoordinatesRepository;
import com.example.tickets.repository.PersonRepository;
import com.example.tickets.repository.TicketRepository;
import com.example.tickets.service.AuthService;
import com.example.tickets.service.RegistrationService;
import com.example.tickets.service.TicketService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.Field;

public class DatabaseInitializer {

    public static AddressRepository addressRepository;
    public static CoordinatesRepository coordinatesRepository;
    public static PersonRepository personRepository;
    public static TicketRepository ticketRepository;
    public static RegistrationService registrationService;
    public static AuthService authService;
    public static TicketService ticketService;

    public static void createBeans(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        // Создание экземпляра репозитория
         addressRepository = new AddressRepository(em);
        coordinatesRepository = new CoordinatesRepository(em);
         personRepository = new PersonRepository(em);
        ticketRepository = new TicketRepository(em);
         registrationService = new RegistrationService(personRepository);
         authService = new AuthService(personRepository);
       ticketService = new TicketService(ticketRepository, personRepository);

    }

    public static void main(String[] args){
        // Получаем класс объекта
        Person person = new Person();
        Class<?> clazz = person.getClass();

        // Получаем поля класса
        Field[] fields = clazz.getDeclaredFields();

        // Выводим информацию о полях
        for (Field field : fields) {
            System.out.println("Field Name: " + field.getName());
            System.out.println("Field Type: " + field.getType().getSimpleName());
        }
    }
//    public static void main(String[] args) {
//        // Создание EntityManagerFactory и EntityManager
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
//        EntityManager em = emf.createEntityManager();
//
//        // Создание экземпляра репозитория
//        AddressRepository addressRepository = new AddressRepository(em);
//        CoordinatesRepository coordinatesRepository = new CoordinatesRepository(em);
//        PersonRepository personRepository = new PersonRepository(em);
//        TicketRepository ticketRepository = new TicketRepository(em);
//        RegistrationService registrationService = new RegistrationService(personRepository);
//        AuthService authService = new AuthService(personRepository);
//        TicketService ticketService = new TicketService(ticketRepository, personRepository);
//
//
//        Person person = new Person();
//        person.setName("Vlad");
//        person.setLogin("Vlad123");
//        person.setPassword("111");
//        registrationService.registerUser(person);
//        Person person1 = new Person();
//        person1.setName("Olga");
//        person1.setLogin("Olga123");
//        person1.setPassword("222");
//        registrationService.registerUser(person1);
//
//        registrationService.setRole(1, Role.ADMIN);
//        registrationService.setRole(2, Role.USER);
//
//
//        System.out.println(authService.loginUser("Vlad123", "111"));
//
//        System.out.println(authService.loginUser("Olga123", "222"));
//
//        Ticket ticket = new Ticket();
//        ticket.setName("Балет");
//
//
//        ticketService.createTicket(ticket, 1);
//
//        Ticket ticket1 = new Ticket();
//        ticket1.setName("Опера");
//        ticketService.createTicket(ticket1, 2);
//
//        ticketService.bookTicket(1, 2);
//
//
//        Ticket ticket2 = new Ticket();
//        ticket2.setName("Опера");
//        ticketService.createTicket(ticket2, 2);
//
//        ticketService.bookTicket(1, 2);
//
//
//        System.out.println(ticketRepository.getAll());














        // Начало транзакции
     //   em.getTransaction().begin();

//        // Выполнение операций с использованием репозитория
//        Address newAddress = new Address("New York", "10001");      // Создаем объект
//        addressRepository.create(newAddress);                                     // Добавляем его в базу данных
//
//        System.out.println(addressRepository.getAll());
//        System.out.println(addressRepository.get(1)) ;
//        Address newAddress1 = new Address("Paris", "20001");
//        newAddress1.setId(1);
//        addressRepository.update(newAddress1); ;
//        System.out.println(addressRepository.get(1)) ;
//        addressRepository.delete(1);
//
//        System.out.println(addressRepository.getAll());
//
//
//
//        coordinatesRepository.create(new Coordinates());
//
//        // Освобождаем ресурсы
//        em.close();
//        emf.close();
//        AddressRepository addressRepository = new AddressRepository();
//
//        Address address = new Address();
//        address.setStreet("Бауманская");
//        address.setZipCode("140000");
//        addressRepository.create(address);
//        addressRepository.trace();
//
//        Address address1 = new Address();
//        address1.setStreet("Ломоносовская");
//        address1.setZipCode("15000");
//        addressRepository.create(address1);
//        addressRepository.trace();
//
//        try {
//            System.out.println(addressRepository.get(3));
//        } catch (GetException e) {
//            System.err.println(e);
//        }
//
//        Address address2 = new Address();
//        address2.setStreet("Тверская");
//        address2.setZipCode("12000");
//        addressRepository.update(address, address2);
//
//        addressRepository.trace();
//
//        addressRepository.delete(3);
//        addressRepository.trace();
//
//        TicketService ticketService = new TicketService();
//        Ticket ticket = new Ticket();
//        ticket.setId(1);
//        ticket.setDiscount(100);
//        ticket.setPrice(500);
//        ticket.setName("Билет на выставку");
//
//
//        Ticket ticket1 = new Ticket();
//        ticket1.setId(2);
//        ticket1.setDiscount(0);
//        ticket1.setPrice(1500);
//        ticket1.setName("Билет в театр");
//        ticketService.addTicket(ticket);
//        ticketService.addTicket(ticket1);
//        System.out.println(ticketService.getTickets());
//
//        try {
//            ticketService.bookTicket(1);
//            ticketService.buyTicket(1);
//        } catch (GetException e) {
//            System.err.println(e);
//        }
//
//        System.out.println(ticketService.getTickets());
//
//        AuthService authService = new AuthService();
//
//        Person person = new Person();
//
//        person.setName("Vlad");
//        person.setLogin("VladUser");
//        person.setPassword("Vlad!123");
//        authService.signIn(person);
//
//        Person person1 = new Person();
//
//        person1.setName("Olga");
//        person1.setLogin("OlgaUser");
//        person1.setPassword("Olga!123");
//        authService.signIn(person1);
//
//        System.out.println(authService.getAllUsers());
//
//        System.out.println(authService.logIn("VladUser1", "Vlad!123"));


   // }
}

 //   static ArrayList<Ticket> tickets = new ArrayList<>();





//    public static void main(String[] args) {
//
//        tickets.add(new Ticket(1, "Балет", new Coordinates(1, 14.f, 15), null,
//                null, null, 100, null, 12, 80, null));
//
//        tickets.add(new Ticket(2, "Балет", new Coordinates(1, 40.f, 120), null,
//                null, null, 200, null, 12, 80, null));
//
//        for (int i = 0; i < tickets.size(); i++) {
//            tickets.get(i).paint();
//        }
//
//    }
//}