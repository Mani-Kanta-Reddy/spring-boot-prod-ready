package com.neon.springbootprodready.service;

import com.neon.springbootprodready.entity.Department;
import com.neon.springbootprodready.exception.DepartmentNotFoundException;
import com.neon.springbootprodready.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService
{
    private final DepartmentRepository repository;

    public Department saveDepartment(Department department)
    {
        return repository.save(department);
    }

    public List<Department> fetchDepartments()
    {
        return repository.findAll();
    }

    public Department fetchDepartmentById(long departmentId) throws DepartmentNotFoundException
    {
        return repository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department with id: " + departmentId + " is not available"));
    }

    public void deleteDepartmentById(long departmentId) throws DepartmentNotFoundException
    {
        Department department = fetchDepartmentById(departmentId);
        repository.deleteById(departmentId);
    }

    public void updateDepartmentById(long departmentId, Department department) throws DepartmentNotFoundException
    {
        Department departmentDB = fetchDepartmentById(departmentId);
        if(department.getName() != null && !department.getName().isEmpty()) {
            departmentDB.setName(department.getName());
        }
        if(department.getCode() != null && !department.getCode().isEmpty()) {
            departmentDB.setCode(department.getCode());
        }
        if(department.getAddress() != null && !department.getAddress().isEmpty()) {
            departmentDB.setAddress(department.getAddress());
        }
        repository.save(departmentDB);
    }
}
