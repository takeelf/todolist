package com.wonho.todolist.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import com.wonho.todolist.TodolistApplication;
import com.wonho.todolist.domain.Todo;
import com.wonho.todolist.request.TodoRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodolistApplication.class)
@AutoConfigureMockMvc
public class TodoServiceTest {

    @Autowired
    TodoService todoService;

    private final String TODO1 = "TODO_SERVICE1";
    private final String TODO2 = "TODO_SERVICE2";
    private final String TODO3 = "TODO_SERVICE3";
    private final String TODO4 = "TODO_SERVICE4";
    private final String TODO5 = "TODO_SERVICE5";
    private final String TODO6 = "TODO_SERVICE6";

    @Before
    public void init() {
        TodoRequest todoRequest1 = new TodoRequest();
        todoRequest1.setContent(TODO1);
        todoService.saveTodo(todoRequest1);

        TodoRequest todoRequest2 = new TodoRequest();
        todoRequest1.setContent(TODO2);
        todoService.saveTodo(todoRequest2);

        TodoRequest todoRequest3 = new TodoRequest();
        todoRequest1.setContent(TODO3);
        todoService.saveTodo(todoRequest3);

        TodoRequest todoRequest4 = new TodoRequest();
        todoRequest1.setContent(TODO4);
        todoService.saveTodo(todoRequest4);

        TodoRequest todoRequest5 = new TodoRequest();
        todoRequest1.setContent(TODO5);
        todoService.saveTodo(todoRequest5);

        TodoRequest todoRequest6 = new TodoRequest();
        todoRequest1.setContent(TODO6);
        todoService.saveTodo(todoRequest6);
    }

    @Test
    public void referTodoTest() {

    }

    @Test
    public void updateTodoServiceTest() {

    }

    @Test
    public void deleteTodoServiceTest() {

    }

}
