package com.wonho.todolist.repository;

import com.wonho.todolist.domain.TodoReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoReferenceRepository extends JpaRepository<TodoReference, Long> {
    List<TodoReference> findByReferredFromId(Long id);
}
