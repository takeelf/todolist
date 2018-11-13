package com.wonho.todolist.repositories;

import com.wonho.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByContent(String content);
}
