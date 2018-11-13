package com.wonho.todolist.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TODO_REFERENCE")
@Data
public class TodoReference {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long referredFromId;

    @ManyToOne
    @JoinColumn(name = "referredToId", nullable = false)
    private Todo referredTo;

}
