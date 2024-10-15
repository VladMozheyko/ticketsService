package com.example.tickets.servlets;


import com.example.tickets.DatabaseInitializer;
import com.example.tickets.model.entities.Person;
import com.example.tickets.model.entities.Ticket;
import com.example.tickets.repository.PersonRepository;
import com.example.tickets.repository.TicketRepository;
import com.example.tickets.service.TicketService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/objects")
public class ListServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String str = request.getMethod();
        JsonObject jsonObject = JsonParser.parseString(str).getAsJsonObject();



      //  Field[] fields = classRequest.getDeclaredFields();

//        // Выводим информацию о полях
//        for (Field field : fields) {
//            System.out.println("Field Name: " + field.getName());
//            System.out.println("Field Type: " + field.getType().getSimpleName());
//        }
//
//        PrintWriter out = response.getWriter();
//      //  List<Ticket> tickets = TicketService.; // Метод для получения объектов из базы данных
//
//        ArrayList<Ticket> tickets = DatabaseInitializer.ticketService.getAllTickets();
//
//        out.println("<html><body>");
//        out.println("<h1>Список объектов</h1>");
//        out.println("<table>");
//        out.println("<tr><th>ID</th><th>Название</th></tr>");

    }
}