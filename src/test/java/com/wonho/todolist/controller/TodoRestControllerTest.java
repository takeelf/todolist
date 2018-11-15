package com.wonho.todolist.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wonho.todolist.TodolistApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodolistApplication.class)
@AutoConfigureMockMvc
public class TodoRestControllerTest {
    @Autowired
    private TodoRestController todoRestController;

    @Autowired
    private MockMvc mvc;

    @Test
    public void restOK() throws Exception {
        mvc.perform(get("/checkHealth")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        String check = this.todoRestController.checkHealth();
        assertEquals("OK", check);
    }

}
