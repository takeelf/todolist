package com.wonho.todolist.repository;

import com.wonho.todolist.TodolistApplication;
import com.wonho.todolist.domain.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodolistApplication.class)
public class TodoRepositoryTest {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoReferenceRepository todoReferenceRepository;

    private final String TODO1 = "TODO1";
    private final String TODO2 = "TODO2";
    private final String TODO3 = "TODO3";
    private final String TODO4 = "TODO4";
    private final String TODO_UPDATE = "TODO_UPDATE";

    @Before
    public void init() {
        Todo todo1 = todoRepository.save(Todo.builder()
                .content(TODO1)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
        todoRepository.save(todo1);
        Todo todo2 = todoRepository.save(Todo.builder()
                .content(TODO2)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
        todoRepository.save(todo2);
        Todo todo3 = todoRepository.save(Todo.builder()
                .content(TODO3)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
        todoRepository.save(todo3);
        Todo todo4 = todoRepository.save(Todo.builder()
                .content(TODO4)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
        todoRepository.save(todo4);
    }

    @Test
    public void findAllTest() {
        Page<Todo> todoList1 = todoRepository.findAll(PageRequest.of(0, 2, Sort.by("id").ascending()));
        assertEquals(2, todoList1.getContent().size());
        Page<Todo> todoList2 = todoRepository.findAll(PageRequest.of(1, 2, Sort.by("id").ascending()));
        assertEquals(2, todoList2.getContent().size());
    }

    @Test
    public void updateTest() {
        Page<Todo> todoList1 = todoRepository.findAll(PageRequest.of(0, 2, Sort.by("id").ascending()));
        Todo targetTodo = todoList1.getContent().get(1);
        targetTodo.setContent(TODO_UPDATE);
        targetTodo = todoRepository.save(targetTodo);
        Todo updatedTodo = todoRepository.findById(targetTodo.getId()).orElse(null);
        assertEquals(TODO_UPDATE, updatedTodo.getContent());
    }

    @Test
    public void deleteTest() {
        Page<Todo> todoList1 = todoRepository.findAll(PageRequest.of(1, 2, Sort.by("id").ascending()));
        Todo targetTodo = todoList1.getContent().get(0);
        todoRepository.delete(targetTodo);
        Long total = todoList1.getTotalElements();
        Page<Todo> deletedList = todoRepository.findAll(PageRequest.of(1, 2, Sort.by("id").ascending()));
        assertEquals(total - 1, deletedList.getTotalElements());
    }

    @Test
    public void findByIdList() {
        Page<Todo> todoList1 = todoRepository.findAll(PageRequest.of(0, 10, Sort.by("id").ascending()));
        List<Long> idList = todoList1.getContent().stream().map(Todo::getId).collect(Collectors.toList());
        List<Todo> todoList2 = todoRepository.findByIdIn(idList);
        assertEquals(todoList1.getContent().size(), todoList2.size());
    }

}
