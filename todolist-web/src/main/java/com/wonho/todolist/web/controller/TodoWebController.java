package com.wonho.todolist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoWebController {

    @GetMapping("/")
    public String indexPage() {
        return "todolist";
    }
}
