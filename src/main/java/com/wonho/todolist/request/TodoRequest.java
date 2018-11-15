package com.wonho.todolist.request;

import java.time.LocalDateTime;
import java.util.List;

import com.wonho.todolist.domain.Todo;

import lombok.Data;

@Data
public class TodoRequest {
    private Long id;

    private String content;

    private Boolean isComplete;

    private List<Long> referenceId;

    private Todo parameterTodo;

    public Todo buildCreateTodo() {
        if (this.isComplete == null) {
            this.isComplete = false;
        }
        return Todo.builder()
                .content(this.content)
                .isComplete(this.isComplete)
                .createdDate(LocalDateTime.now())
                .build();
    }

    public Todo buildUpdateTodo(Todo todo) {
        if (this.content != null) {
            todo.setContent(this.content);
        }
        if (this.isComplete != null) {
            todo.setIsComplete(this.isComplete);
        }
        todo.setUpdatedDate(LocalDateTime.now());
        return todo;
    }

}
