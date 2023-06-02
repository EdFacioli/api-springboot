package com.benice.api.helper.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class Errors {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> error404() {

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400(MethodArgumentNotValidException ex) {
        
        return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(ApiErrors::new));
    }
}
