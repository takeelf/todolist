package com.wonho.todolist.repository;

import com.wonho.todolist.domain.TodoReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoReferenceRepository extends JpaRepository<TodoReference, Long> {
    void deleteByReferredFromId(Long id);

    void deleteByReferredToId(Long id);

    List<TodoReference> findByReferredFromId(Long id);

    List<TodoReference> findByReferredToId(Long id);
}
