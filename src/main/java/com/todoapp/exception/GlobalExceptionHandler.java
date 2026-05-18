package com.todoapp.exception;

import com.todoapp.exception.specific.BusinessException;
import com.todoapp.exception.specific.ConflictException;
import com.todoapp.exception.specific.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        log.warn("Resource Not Found: {}", e.getMessage());

        ExceptionResponse exResponse = new  ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                "RESOURCE_NOT_FOUND",
                e.getMessage(),
                request.getDescription(false),
               LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exResponse);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionResponse> handleConflictException(ConflictException e, WebRequest request) {
        log.warn("Conflict Exception: {}", e.getMessage());

        ExceptionResponse exResponse = new  ExceptionResponse(
                HttpStatus.CONFLICT.value(),
                "CONFLICT_EXCEPTION",
                e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException e, WebRequest request) {
        log.warn("Business Exception: {}", e.getMessage());

        ExceptionResponse exResponse = new  ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                "BUSINESS_EXCEPTION",
                e.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e, WebRequest request) {
        log.error("System Exception: {}", e.getMessage());

        ExceptionResponse exResponse = new  ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred. Please try again later.",
                request.getDescription(false),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        log.warn("Method Argument Not Valid Exception: {}", e.getMessage());

        HashMap<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ExceptionResponse exResponse = new  ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_EXCEPTION",
                errors,
                request.getDescription(false),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException e, WebRequest request) {
        log.warn("Bad Credentials Exception: {}", e.getMessage());

        ExceptionResponse exResponse = new  ExceptionResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "BAD_CREDENTIALS_EXCEPTION",
                "Invalid username or password.",
                request.getDescription(false),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
        log.warn("Access Denied Exception: {}", e.getMessage());

        ExceptionResponse exResponse = new  ExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                "ACCESS_DENIED_EXCEPTION",
                "You do not have permission to access this resource.",
                request.getDescription(false),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exResponse);
    }
}
