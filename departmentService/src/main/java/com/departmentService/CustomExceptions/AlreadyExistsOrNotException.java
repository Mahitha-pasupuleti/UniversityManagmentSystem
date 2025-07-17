package com.departmentService.CustomExceptions;

public class AlreadyExistsOrNotException extends RuntimeException{
    public AlreadyExistsOrNotException(String message) {
        super(message);
    }
}
