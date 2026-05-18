    package com.todoapp.exception.specific;

    public class ConflictException extends RuntimeException {
        public ConflictException(String message) {
            super(message);
        }
    }
