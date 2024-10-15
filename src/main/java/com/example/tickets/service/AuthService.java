package com.example.tickets.service;


import com.example.tickets.UtilRepository;
import com.example.tickets.model.entities.Person;
import com.example.tickets.model.enums.Role;
import com.example.tickets.repository.PersonRepository;

/**
 * Класс для авторизации пользователя
 */

public class AuthService {
    private PersonRepository personRepository;


    public AuthService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String loginUser(String login, String password){
        Person person = personRepository.findByLoginAndPassword(login, UtilRepository.hashPassword(password));
        if(person.getRole().equals(Role.USER)){
            return "user";
        }
        else
            return "admin";
    }
}
