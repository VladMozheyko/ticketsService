package com.example.tickets.service;

import com.example.tickets.model.entities.Person;
import com.example.tickets.model.enums.Role;
import com.example.tickets.repository.PersonRepository;

/**
 * Класс для регистрации пользователей. По умолчанию в системе предусмотрен один админ
 */

public class RegistrationService {
    private PersonRepository personRepository;


    public RegistrationService(PersonRepository repository) {
        this.personRepository = repository;
    }

    public void registerUser(Person person){
        personRepository.create(person);
    }

    public void setRole(int id, Role role){
        Person person = personRepository.get(id);
        person.setRole(role);
        personRepository.update(person);
    }

}
