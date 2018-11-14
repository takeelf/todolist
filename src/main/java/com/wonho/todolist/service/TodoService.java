package com.wonho.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wonho.todolist.repository.TodoRepository;

@Component
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public String checkHealth() {
        return "OK";
    }
}
