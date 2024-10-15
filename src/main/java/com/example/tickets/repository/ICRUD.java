package com.example.tickets.repository;

import com.example.tickets.exceptions.GetException;

import java.util.ArrayList;

public interface ICRUD<T, ID> {


     void create(T object);                    // Use T for object types

     void update(T newObject);            // Use T for both old and new objects

     void delete(ID id);                        // Use ID type for the ID

     T get(ID id) throws GetException;          // Use T as the return type and ID as the parameter

     ArrayList<T> getAll();                     // Return a list of T objects


}