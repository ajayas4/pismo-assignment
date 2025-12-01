package com.pismo.assignment.handler;

import com.pismo.assignment.exception.InternalServerException;
import com.pismo.assignment.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<?> handle(InternalServerException ex){
        return ResponseEntity.status(ex.getCode()).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handle(ResourceNotFoundException ex){
        return ResponseEntity.status(ex.getCode()).body(ex.getMessage());
    }
}
