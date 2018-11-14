package com.wonho.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wonho.todolist.domain.Todo;
import com.wonho.todolist.service.TodoService;

@RestController
public class TodoRestController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/checkHealth")
    public String checkHealth() {
        return todoService.checkHealth();
    }

    @GetMapping("/")
    public List<Todo> getTodoList() {
        return null;
    }
}
