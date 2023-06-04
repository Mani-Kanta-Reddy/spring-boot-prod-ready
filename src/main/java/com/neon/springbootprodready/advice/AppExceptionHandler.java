package com.neon.springbootprodready.advice;

import com.neon.springbootprodready.exception.DepartmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = {
    "com.neon.springbootprodready.controller"
})
public class AppExceptionHandler
{
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> departmentNotFound(DepartmentNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of(
                "errorMessage",
                exception.getMessage()
            ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> badPayload(MethodArgumentNotValidException exception) {
        Map<String, Object> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorMap);
    }
}
