package com.wonho.todolist.domain;

import com.wonho.todolist.repositories.TodoReferenceRepository;
import com.wonho.todolist.repositories.TodoRepository;
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
public class TodoTest {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoReferenceRepository todoReferenceRepository;

    private final String REFERRED_FROM_CONTENT = "FromContent";
    private final String REFERRED_TO_CONTENT = "ToContent";

    @Before
    public void init() {
        Todo toTodo = todoRepository.save(Todo.builder()
                .content(REFERRED_TO_CONTENT)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());

        Todo fromTodo = Todo.builder()
                .content(REFERRED_FROM_CONTENT)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        fromTodo.addReference(TodoReference.builder()
                    .referredToId(toTodo.getId())
                    .build());
        todoRepository.save(fromTodo);
    }

    @Test
    public void TestCreatedAndFind() {
        Todo fromTodo = todoRepository.findByContent(REFERRED_FROM_CONTENT);
        assertEquals(REFERRED_FROM_CONTENT, fromTodo.getContent());
        Todo toTodo = todoRepository.findById(
                ((TodoReference)fromTodo.getReferences().toArray()[0]).getReferredToId()
        ).get();
        assertEquals(REFERRED_TO_CONTENT, toTodo.getContent());

    }
}
