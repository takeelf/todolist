package com.wonho.todolist;

import com.wonho.todolist.request.TodoRequest;
import com.wonho.todolist.service.TodoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(TodoService todoService) {
        return (args) -> {
            IntStream.rangeClosed(1, 2).forEach(index -> {
                TodoRequest todoRequest = new TodoRequest();
                todoRequest.setContent("Sample Todo" + index);
                todoService.saveTodo(todoRequest);
            });
        };
    }
}
