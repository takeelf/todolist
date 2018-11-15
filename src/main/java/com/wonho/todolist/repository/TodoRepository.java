package com.wonho.todolist.repository;

import java.util.List;

import com.wonho.todolist.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findAll(Pageable pageable);

    Todo findByContent(String content);

    List<Todo> findByIdIn(List<Long> idList);
}
