package com.example.tickets.repository;

import com.example.tickets.exceptions.GetException;
import com.example.tickets.model.entities.Coordinates;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesRepository  implements ICRUD<Coordinates, Integer>{


    private EntityManager entityManager;

    public CoordinatesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Coordinates coordinates) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(coordinates);  // Сохранение сущности
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Откат транзакции при ошибке
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Coordinates coordinates) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(coordinates);  // Обновление сущности
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
            Coordinates coordinates = entityManager.find(Coordinates.class, id);  // Поиск сущности по ID
            if (coordinates != null) {
                entityManager.remove(coordinates);  // Удаление сущности
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
    public Coordinates get(Integer id) throws GetException {
        Coordinates coordinates = null;
        try {
            coordinates = entityManager.find(Coordinates.class, id);  // Поиск сущности по ID
            if (coordinates == null) {
                throw new GetException("Coordinates with ID " + id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Error retrieving Coordinates with ID " + id);
        }
        return coordinates;
    }

    @Override
    public ArrayList<Coordinates> getAll() {
        List<Coordinates> coordinatesList = new ArrayList<>();
        try {
            coordinatesList = entityManager.createQuery("from Coordinates", Coordinates.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(coordinatesList);  // Возвращаем список всех координат
    }

}
