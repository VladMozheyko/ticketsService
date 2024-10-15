package com.example.tickets.repository;

import com.example.tickets.exceptions.GetException;
import com.example.tickets.model.entities.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class EventRepository implements  ICRUD<Event, Integer> {

    private EntityManager entityManager;

    public EventRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Event event) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(event);  // Сохранение сущности
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Откат транзакции при ошибке
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(event);  // Обновление сущности
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Откат транзакции при ошибке
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Event event = entityManager.find(Event.class, id);  // Поиск сущности по ID
            if (event != null) {
                entityManager.remove(event);  // Удаление сущности
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Откат транзакции при ошибке
            }
            e.printStackTrace();
        }
    }

    @Override
    public Event get(Integer id) throws GetException {
        Event event = null;
        try {
            event = entityManager.find(Event.class, id);  // Поиск сущности по ID
            if (event == null) {
                throw new GetException("Event with ID " + id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Error retrieving Event with ID " + id);
        }
        return event;
    }

    @Override
    public ArrayList<Event> getAll() {
        List<Event> eventList = new ArrayList<>();
        try {
            eventList = entityManager.createQuery("from Event", Event.class).getResultList();  // Получение всех записей
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(eventList);  // Возвращаем список всех событий
    }

}
