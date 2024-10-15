package com.example.tickets.repository;


import com.example.tickets.exceptions.GetException;
import com.example.tickets.model.entities.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

// Аналогия с кафе. Мы в интерфейсе заказываем конкретный тип - Ticket
public class TicketRepository implements ICRUD <Ticket, Integer>{


        private EntityManager entityManager;

        public TicketRepository(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        public void create(Ticket ticket) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                ticket.setCreationDate(new java.util.Date()); // Автоматически устанавливаем дату создания
                entityManager.persist(ticket);  // Сохранение сущности
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();  // Откат транзакции при ошибке
                }
                e.printStackTrace();
            }
        }

        @Override
        public void update(Ticket ticket) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.merge(ticket);  // Обновление сущности
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
                Ticket ticket = entityManager.find(Ticket.class, id);  // Поиск сущности по ID
                if (ticket != null) {
                    entityManager.remove(ticket);  // Удаление сущности
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
        public Ticket get(Integer id) throws GetException {
            Ticket ticket = null;
            try {
                ticket = entityManager.find(Ticket.class, id);  // Поиск сущности по ID
                if (ticket == null) {
                    throw new GetException("Ticket with ID " + id + " not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new GetException("Error retrieving Ticket with ID " + id);
            }
            return ticket;
        }

        @Override
        public ArrayList<Ticket> getAll() {
            List<Ticket> ticketList = new ArrayList<>();
            try {
                ticketList = entityManager.createQuery("from Ticket", Ticket.class).getResultList();  // Получение всех записей
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ArrayList<>(ticketList);  // Возвращаем список всех билетов
        }

}