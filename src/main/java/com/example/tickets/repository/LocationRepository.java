package com.example.tickets.repository;

import com.example.tickets.exceptions.GetException;
import com.example.tickets.model.entities.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class LocationRepository implements ICRUD<Location, Integer> {
    private EntityManager entityManager;

    public LocationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Location location) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(location);  // Сохранение сущности
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Откат транзакции при ошибке
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Location location) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(location);  // Обновление сущности
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
            Location location = entityManager.find(Location.class, id);  // Поиск сущности по ID
            if (location != null) {
                entityManager.remove(location);  // Удаление сущности
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
    public Location get(Integer id) throws GetException {
        Location location = null;
        try {
            location = entityManager.find(Location.class, id);  // Поиск сущности по ID
            if (location == null) {
                throw new GetException("Location with ID " + id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Error retrieving Location with ID " + id);
        }
        return location;
    }

    @Override
    public ArrayList<Location> getAll() {
        List<Location> locationList = new ArrayList<>();
        try {
            locationList = entityManager.createQuery("from Location", Location.class).getResultList();  // Получение всех записей
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(locationList);  // Возвращаем список всех локаций
    }
}