package com.wonho.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wonho.todolist.domain.Todo;
import com.wonho.todolist.domain.TodoReference;
import com.wonho.todolist.repository.TodoReferenceRepository;
import com.wonho.todolist.repository.TodoRepository;
import com.wonho.todolist.request.TodoRequest;

@Component
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoReferenceRepository todoReferenceRepository;

    public String checkHealth() {
        return "OK";
    }

    public Todo saveTodo(TodoRequest todoRequest) {
        return todoRepository.save(todoRequest.buildCreateTodo());
    }

    @Transactional
    public Todo updateTodo(TodoRequest todoRequest) {
        Todo todo = todoRepository.findById(todoRequest.getId()).get();
        todo = todoRequest.buildUpdateTodo(todo);
        todo = todoRepository.save(todo);
        todoRequest.setParameterTodo(todo);
        if (todoRequest.getReferenceId() != null) {
            return this.referTodo(todoRequest);
        }
        return todo;
    }

    public Todo referTodo(TodoRequest todoRequest) {
        Todo todo = null;
        if (todoRequest.getParameterTodo() == null) {
            todo = todoRepository.findById(todoRequest.getId()).get();
        }
        todo.setReferences(null);
        List<Long> referenceId = todoRequest.getReferenceId();
        List<Todo> referenceTodoList = todoRepository.findByIdIn(referenceId);
        List<Long> confirmedId = referenceTodoList.stream().map(Todo::getId).collect(Collectors.toList());
        for (Long todoId : confirmedId) {
            TodoReference todoReference = new TodoReference();
            todoReference.setReferredFrom(todo);
            todoReference.setReferredToId(todoId);
            todoReference = todoReferenceRepository.save(todoReference);
            todo.addReference(todoReference);
        }
        return todoRepository.save(todo);
    }

    public void deleteTodo(TodoRequest todoRequest) {
        todoRepository.deleteById(todoRequest.getId());
    }

}
