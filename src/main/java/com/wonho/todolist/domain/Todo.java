package com.wonho.todolist.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "TODO")
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

}
