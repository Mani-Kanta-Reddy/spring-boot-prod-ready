package com.neon.springbootprodready.controller;

import com.neon.springbootprodready.entity.Department;
import com.neon.springbootprodready.exception.DepartmentNotFoundException;
import com.neon.springbootprodready.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController
{

    private final DepartmentService service;

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
        return new ResponseEntity<>(service.saveDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> fetchDepartments() {
        return ResponseEntity.ok(service.fetchDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> fetchDepartmentById(@PathVariable(name = "id") long departmentId) throws DepartmentNotFoundException
    {
        return ResponseEntity.ok(service.fetchDepartmentById(departmentId));
    }

    @DeleteMapping("{id}")
    public void deleteDepartmentById(@PathVariable(name = "id") long departmentId) throws DepartmentNotFoundException
    {
        service.deleteDepartmentById(departmentId);
    }

    @PutMapping("{id}")
    public void updateDepartmentById(@PathVariable(name = "id") long departmentId, @RequestBody Department department) throws DepartmentNotFoundException
    {
        service.updateDepartmentById(departmentId, department);
    }
}
