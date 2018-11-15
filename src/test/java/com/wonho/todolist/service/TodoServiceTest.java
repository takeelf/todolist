package com.wonho.todolist.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wonho.todolist.TodolistApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodolistApplication.class)
@AutoConfigureMockMvc
public class TodoServiceTest {

}
