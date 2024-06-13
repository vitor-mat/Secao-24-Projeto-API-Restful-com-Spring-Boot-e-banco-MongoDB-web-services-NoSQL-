package com.springcourse.workshopmongo.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(NotFoundException notFoundException, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", notFoundException.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
