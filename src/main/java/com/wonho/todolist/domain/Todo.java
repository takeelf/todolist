package com.wonho.todolist.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TODO")
@Data
@NoArgsConstructor
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

    @OneToMany(mappedBy = "referredFrom", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<TodoReference> references;

    public void addReference(TodoReference todoReference) {
        if (this.references == null) {
            this.references = new ArrayList<>();
        }
        this.references.add(todoReference);
    }

    public String toString() {
        return Long.toString(id) + ":" + content;
    }

    @Builder
    public Todo(String content,
                Boolean isComplete,
                LocalDateTime createdDate,
                LocalDateTime updatedDate) {
        this.content = content;
        this.isComplete = isComplete;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

}
