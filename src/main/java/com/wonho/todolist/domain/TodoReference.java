package com.wonho.todolist.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Todo referredFrom;

    @Column
    private Long referredToId;

    public String toString() {
        return Long.toString(referredFrom.getId()) + ":" + Long.toString(referredToId);
    }
}
