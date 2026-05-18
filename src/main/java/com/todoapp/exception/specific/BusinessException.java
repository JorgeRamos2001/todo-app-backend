package com.todoapp.exception.specific;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
