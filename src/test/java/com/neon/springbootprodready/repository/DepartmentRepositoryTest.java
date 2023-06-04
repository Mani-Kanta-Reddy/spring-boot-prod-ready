package com.neon.springbootprodready.repository;

import com.neon.springbootprodready.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest
{

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private TestEntityManager entityManager;
    @BeforeEach
    public void setUp() {
        Department department = new Department(0L, "CSE", "THANJAVUR", "CSE-A");    //ID ZERO is overridden with generated SEQUENCE
        entityManager.persist(department);
    }

    @Test
    public void givenValidId_fetchDepartment() {
        long id = 1L;
        Department result = repository.findById(1L).orElseThrow();
        assertEquals(1L, result.getId());
    }

}