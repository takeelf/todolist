package com.wonho.todolist.repository;

import com.wonho.todolist.domain.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryTest {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoReferenceRepository todoReferenceRepository;

    private final String TODO1 = "TODO1";
    private final String TODO2 = "TODO2";
    private final String TODO3 = "TODO3";
    private final String TODO4 = "TODO4";

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
                .content(TODO1)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
        todoRepository.save(todo2);
        Todo todo3 = todoRepository.save(Todo.builder()
                .content(TODO1)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
        todoRepository.save(todo3);
        Todo todo4 = todoRepository.save(Todo.builder()
                .content(TODO1)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
        todoRepository.save(todo4);
    }

    @Test
    public void findAllTest() {

    }

    @Test
    public void updateTest() {

    }

    @Test
    public void deleteTest() {

    }

}
