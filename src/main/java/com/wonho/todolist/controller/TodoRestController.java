package com.wonho.todolist.controller;

import java.util.List;

import com.wonho.todolist.request.ListRequest;
import com.wonho.todolist.request.TodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wonho.todolist.domain.Todo;
import com.wonho.todolist.service.TodoService;

@RestController
@RequestMapping("/api/todo")
public class TodoRestController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/checkHealth")
    public String checkHealth() {
        return todoService.checkHealth();
    }

    @GetMapping("/")
    public Page<Todo> getTodoList(ListRequest listRequest) {
        return todoService.getTodoList(listRequest);
    }

    @PostMapping
    public Todo saveTodo(TodoRequest todoRequest) {
        return todoService.saveTodo(todoRequest);
    }

    @GetMapping("/{id}")
    public Todo getTodo(TodoRequest todoRequest) {
        return todoService.getTodo(todoRequest);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(TodoRequest todoRequest) {
        return todoService.updateTodo(todoRequest);
    }

    @PutMapping("/{id}/reference")
    public Todo referTodo(TodoRequest todoRequest) {
        return todoService.referTodo(todoRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(TodoRequest todoRequest) {
        todoService.deleteTodo(todoRequest);
        return new ResponseEntity<>("{}", HttpStatus.NO_CONTENT);
    }

}
