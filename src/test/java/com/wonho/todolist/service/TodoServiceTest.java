package com.wonho.todolist.service;

import com.wonho.todolist.exception.IsCompleteException;
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
import java.util.ArrayList;
import java.util.List;

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
    private final String TODO7 = "TODO_SERVICE7";

    @Before
    public void init() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setContent(TODO1);
        todoService.saveTodo(todoRequest);

        todoRequest.setContent(TODO2);
        todoService.saveTodo(todoRequest);

        todoRequest.setContent(TODO3);
        todoService.saveTodo(todoRequest);

        todoRequest.setContent(TODO4);
        todoService.saveTodo(todoRequest);

        todoRequest.setContent(TODO5);
        Todo todo5 = todoService.saveTodo(todoRequest);

        todoRequest.setContent(TODO6);
        Todo todo6 = todoService.saveTodo(todoRequest);

        todoRequest.setContent(TODO7);
        Todo todo7 = todoService.saveTodo(todoRequest);

        List<Long> refList = new ArrayList<>();
        refList.add(todo5.getId());
        refList.add(todo7.getId());
        todoRequest = new TodoRequest();
        todoRequest.setId(todo6.getId());
        todoRequest.setReferenceId(refList);
        todoService.referTodo(todoRequest);
    }

    @Test
    public void referTodoTest() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setContent(TODO1);
        Todo todo1 = todoService.getTodoByContent(todoRequest).get(0);
        todoRequest.setContent(TODO2);
        Todo todo2 = todoService.getTodoByContent(todoRequest).get(0);
        todoRequest.setContent(TODO3);
        Todo todo3 = todoService.getTodoByContent(todoRequest).get(0);
        todoRequest.setContent(TODO4);
        Todo todo4 = todoService.getTodoByContent(todoRequest).get(0);

        List<Long> refList = new ArrayList<>();
        //할일 2번은 1번에 참조가 걸림
        refList.add(todo1.getId());
        todoRequest.setId(todo2.getId());
        todoRequest.setReferenceId(refList);
        Todo test1 = todoService.referTodo(todoRequest);
        assertEquals(todo2.getId(), test1.getReferences().get(0).getReferredFrom().getId());
        assertEquals(todo1.getId(), test1.getReferences().get(0).getReferredToId());
        //할일 3번은 1번에 참조가 걸림
        todoRequest.setId(todo3.getId());
        Todo test2 = todoService.referTodo(todoRequest);
        assertEquals(todo3.getId(), test2.getReferences().get(0).getReferredFrom().getId());
        assertEquals(todo1.getId(), test2.getReferences().get(0).getReferredToId());
        //할일 4번은 1,3번에 참조가 걸림
        todoRequest.setId(todo4.getId());
        refList = new ArrayList<>();
        refList.add(todo1.getId());
        refList.add(todo3.getId());
        todoRequest.setReferenceId(refList);
        Todo test3 = todoService.referTodo(todoRequest);
        assertEquals(todo4.getId(), test3.getReferences().get(0).getReferredFrom().getId());
        assertEquals(todo1.getId(), test3.getReferences().get(0).getReferredToId());
        assertEquals(todo3.getId(), test3.getReferences().get(1).getReferredToId());
    }

    @Test
    public void updateTodoServiceTest() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setContent(TODO7);
        Todo todo7 = todoService.getTodoByContent(todoRequest).get(0);
        todoRequest = new TodoRequest();
        todoRequest.setId(todo7.getId());
        todoRequest.setIsComplete(true);
        try {
            todoService.updateTodo(todoRequest);
        } catch (IsCompleteException ice) {
            TodoRequest updateRequest = new TodoRequest();
            updateRequest.setContent(TODO6);
            Todo todo6 = todoService.getTodoByContent(updateRequest).get(0);
            updateRequest = new TodoRequest();
            updateRequest.setId(todo6.getId());
            updateRequest.setIsComplete(true);
            todo6 = todoService.updateTodo(updateRequest);
            assertEquals(true, todo6.getIsComplete());
            todo7 = todoService.updateTodo(todoRequest);
            assertEquals(true, todo7.getIsComplete());
            return;
        }
        assertEquals("ERROR", null);
    }

    @Test
    public void deleteTodoServiceTest() {
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setContent(TODO5);
        Todo todo5 = todoService.getTodoByContent(todoRequest).get(0);
        TodoRequest deleteRequest = new TodoRequest();
        deleteRequest.setId(todo5.getId());
        todoService.deleteTodo(deleteRequest);
        Todo deletedTodo = todoService.getTodo(deleteRequest);
        assertNull(deletedTodo);
    }

}
