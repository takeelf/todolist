package com.wonho.todolist.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Entity
@Table
@Builder
public class Todo implements Serializable {
    private static final long serialVersionUID = -4542748891757151161L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private Boolean isComplete;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "referredFromId")
    private Collection<TodoReference> references;

    public void addReference(TodoReference todoReference) {
        if (this.references == null) {
            this.references = new ArrayList<>();
        }
        this.references.add(todoReference);
    }
}
