package com.neon.springbootprodready.controller;

import com.neon.springbootprodready.entity.Department;
import com.neon.springbootprodready.exception.DepartmentNotFoundException;
import com.neon.springbootprodready.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService service;

    private Department department;

    @BeforeEach
    void setUp()
    {
        department = new Department(1L, "CSE", "THANJAVUR", "CSE-A");
    }

    @Test
    void saveDepartment() throws Exception
    {
        Department inputDepartment = new Department(1L, "CSE", "THANJAVUR", "CSE-A");
        Mockito.when(service.saveDepartment(inputDepartment)).thenReturn(department);
        mockMvc.perform(post("/api/v1/department")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                "    \"name\": \"CSE\",\n" +
                "    \"address\": \"THANJAVUR\",\n" +
                "    \"code\": \"CSE-A\"\n" +
                "}"))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void fetchDepartmentById() throws Exception
    {
        long id = 1L;
        Mockito.when(service.fetchDepartmentById(id)).thenReturn(department);
        mockMvc.perform(get("/api/v1/department/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(department.getName()));
    }
}