package com.example.tickets.repository;

import com.example.tickets.exceptions.GetException;
import com.example.tickets.model.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements ICRUD<Person, Integer> {


        private EntityManager entityManager;

        public PersonRepository(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        public void create(Person person) {
            person.setColor();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(person);  // Сохранение сущности
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();  // Откат транзакции при ошибке
                }
                e.printStackTrace();
            }
        }

        @Override
        public void update(Person person) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.merge(person);  // Обновление сущности
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
                Person person = entityManager.find(Person.class, id);  // Поиск сущности по ID
                if (person != null) {
                    entityManager.remove(person);  // Удаление сущности
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
        public Person get(Integer id)  {
            Person person = null;
            try {
                person = entityManager.find(Person.class, id);  // Поиск сущности по ID
                if (person == null) {
                    throw new GetException("Person with ID " + id + " not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return person;
        }

        @Override
        public ArrayList<Person> getAll() {
            List<Person> personList = new ArrayList<>();
            try {
                personList = entityManager.createQuery("from Person", Person.class).getResultList();  // Получение всех записей
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ArrayList<>(personList);  // Возвращаем список всех людей
        }

    @Transactional
    public Person findByLoginAndPassword(String login, String password) {
        String sql = "SELECT * FROM person p WHERE p.login = :login AND p.password = :password";

        Query query = entityManager.createNativeQuery(sql, Person.class);
        query.setParameter("login", login);
        query.setParameter("password", password);

        // Возвращает единственный результат, может выбросить исключение NoResultException, если запись не найдена
        List<Person> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}