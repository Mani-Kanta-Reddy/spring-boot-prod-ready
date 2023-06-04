package com.neon.springbootprodready.service;

import com.neon.springbootprodready.entity.Department;
import com.neon.springbootprodready.exception.DepartmentNotFoundException;
import com.neon.springbootprodready.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest
{
    @Mock
    private DepartmentRepository repository;
    @InjectMocks
    private DepartmentService service;

    @Test
    public void givenDepartment_thenSaveTheDepartment()
    {
        //Prepare
        Department department = new Department(1L, "CSE", "THANJAVUR", "CSE-A");
        Mockito.when(repository.save(department)).thenReturn(department);
        //Perform
        Department result = service.saveDepartment(department);
        //Assert
        assertEquals(department, result);
        Mockito.verify(repository, Mockito.times(1)).save(department);
    }

    @Test
    @DisplayName(value = "Get Department Details for a valid Department Id")
    public void givenValidID_thenReturnDepartment() throws DepartmentNotFoundException
    {
        //Prepare
        long id = 1L;
        Department department = new Department(1L, "CSE", "THANJAVUR", "CSE-A");
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(department));
        //Perform
        Department result = service.fetchDepartmentById(id);
        //Assert
        assertEquals(department, result);
        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

}