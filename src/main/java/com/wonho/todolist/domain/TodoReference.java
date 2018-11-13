package com.wonho.todolist.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table
@Builder
public class TodoReference {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long referredFromId;

    @Column
    private Long referredToId;

}
