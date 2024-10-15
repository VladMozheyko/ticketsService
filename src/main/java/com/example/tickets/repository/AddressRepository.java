package com.example.tickets.repository;

import com.example.tickets.model.entities.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;


public class AddressRepository implements ICRUD<Address, Integer>{

    // Использование SessionFactory для управления сессиями Hibernate
    private EntityManager entityManager;


    /**
     * Конструтктор
     * @param entityManager - менеджер сущностей для работы с базой данных
     */
    public AddressRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Метод для добавления адреса
     * @param address новый адрес
     */
    @Override
    public void create(Address address) {
        EntityTransaction transaction = entityManager.getTransaction();               // Начинаем транзакцию для записи в БД
        try {
            transaction.begin();
            entityManager.persist(address);                                           // Сохранение сущности
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();                                              // Откат транзакции при ошибке
            }
            e.printStackTrace();
        }
    }



    /**
     * Метод для обновления сущности
     * @param address
     */
    @Override
    public void update(Address address) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(address);                                          // Обновление сущности
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Метод для поиска по идентификатору
     * @param id
     */
    @Override
    public void delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Address address = entityManager.find(Address.class, id);            // Поиск сущности по ID
            if (address != null) {
                entityManager.remove(address);                                  // Удаление сущности
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    /**
     * Метод для получения сущности
     * @param id идентификатор
     * @return сущность
     */
    @Override
    public Address get(Integer id) {
        return entityManager.find(Address.class, id);                            // Получение сущности по ID
    }

    /**
     * Метод для получения всех записей в таблице
     * @return содержимое таблицы
     */
    @Override
    public ArrayList<Address> getAll() {
        return (ArrayList<Address>) entityManager.createQuery("from Address", Address.class).getResultList(); // Получение всех записей
    }


}
