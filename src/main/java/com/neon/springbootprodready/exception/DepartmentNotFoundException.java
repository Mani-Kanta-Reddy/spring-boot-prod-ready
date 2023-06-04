package com.neon.springbootprodready.exception;

public class DepartmentNotFoundException extends Exception
{
    public DepartmentNotFoundException(String message)
    {
        super(message);
    }
}
