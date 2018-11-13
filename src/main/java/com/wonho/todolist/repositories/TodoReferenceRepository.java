package com.wonho.todolist.repositories;

import com.wonho.todolist.domain.TodoReference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoReferenceRepository extends JpaRepository<TodoReference, Long> {
}
