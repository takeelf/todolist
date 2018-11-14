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

    @ManyToOne
    @JoinColumn(name = "referredFromId")
    private Todo referredFrom;

    @Column
    private Long referredToId;

    public String toString() {
        return Long.toString(referredFrom.getId()) + ":" + Long.toString(referredToId);
    }
}
