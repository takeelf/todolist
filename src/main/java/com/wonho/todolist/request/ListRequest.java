package com.wonho.todolist.request;

import lombok.Data;

@Data
public class ListRequest {
    private int size;
    private int page;
}
