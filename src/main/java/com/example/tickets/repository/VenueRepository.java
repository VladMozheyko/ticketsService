package com.example.tickets.repository;

import com.example.tickets.exceptions.GetException;
import com.example.tickets.model.entities.Venue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class VenueRepository implements ICRUD<Venue, Integer> {

    private EntityManager entityManager;

    public VenueRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Venue venue) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(venue);  // Сохранение сущности
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Откат транзакции при ошибке
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Venue venue) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(venue);  // Обновление сущности
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
            Venue venue = entityManager.find(Venue.class, id);  // Поиск сущности по ID
            if (venue != null) {
                entityManager.remove(venue);  // Удаление сущности
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
    public Venue get(Integer id) throws GetException {
        Venue venue = null;
        try {
            venue = entityManager.find(Venue.class, id);  // Поиск сущности по ID
            if (venue == null) {
                throw new GetException("Venue with ID " + id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Error retrieving Venue with ID " + id);
        }
        return venue;
    }

    @Override
    public ArrayList<Venue> getAll() {
        List<Venue> venueList = new ArrayList<>();
        try {
            venueList = entityManager.createQuery("from Venue", Venue.class).getResultList();  // Получение всех записей
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(venueList);  // Возвращаем список всех объектов Venue
    }
}