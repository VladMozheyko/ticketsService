package com.example.tickets;

import com.example.tickets.servlets.ListServlet;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

// Указываем базовый путь для всех RESTful сервисов
@ApplicationPath("/api")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        DatabaseInitializer.createBeans();
        Set<Class<?>> classes = new HashSet<>();
        // Добавьте ваши RESTful сервлеты (ресурсы) сюда
        classes.add(ListServlet.class);  //
        // Можно добавить другие сервлеты аналогично
        return classes;
    }
}
