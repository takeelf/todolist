package com.wonho.todolist.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ListRequest {
    private int size = 10;
    private int page = 1;

    public Pageable buildPageable() {
        return PageRequest.of(this.page - 1, this.size, Sort.by("id").ascending());
    }
}
