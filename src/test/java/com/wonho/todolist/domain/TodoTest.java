package com.wonho.todolist.domain;

import com.wonho.todolist.repository.TodoReferenceRepository;
import com.wonho.todolist.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoTest {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoReferenceRepository todoReferenceRepository;

    private final String REFERRED_FROM_CONTENT = "FromContent";
    private final String REFERRED_TO_CONTENT1 = "ToContent1";
    private final String REFERRED_TO_CONTENT2 = "ToContent2";

    // 1,2,3의 Todo를 생성하고 2번의 todo에 1,3번을 참조 저장
    @Before
    public void init() {
        Todo toTodo = todoRepository.save(Todo.builder()
                .content(REFERRED_TO_CONTENT1)
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


        fromTodo = todoRepository.save(fromTodo);
        TodoReference todoReference1 = new TodoReference();
        todoReference1.setReferredFrom(fromTodo);
        todoReference1.setReferredToId(toTodo.getId());
        TodoReference savedTodoReference = todoReferenceRepository.save(todoReference1);
        fromTodo.addReference(savedTodoReference);
        fromTodo = todoRepository.save(fromTodo);

        Todo toTodo2 = todoRepository.save(Todo.builder()
                .content(REFERRED_TO_CONTENT2)
                .isComplete(false)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());

        TodoReference todoReference2 = new TodoReference();
        todoReference2.setReferredToId(toTodo2.getId());
        todoReference2.setReferredFrom(fromTodo);
        savedTodoReference = todoReferenceRepository.save(todoReference2);
        fromTodo.addReference(savedTodoReference);
        todoRepository.save(fromTodo);

    }

    @Test
    public void TestCreatedAndFind() {
        Todo fromTodo = todoRepository.findByContent(REFERRED_FROM_CONTENT);
        assertEquals(REFERRED_FROM_CONTENT, fromTodo.getContent());
        List<TodoReference> todoReferences = todoReferenceRepository.findByReferredFromId(fromTodo.getId());
        assertEquals(2, todoReferences.size());

    }
}
