package org.abhijeet.ecomapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,Object>> productNotFoundException(ProductNotFoundException ex){
        Map<String,Object> error = Map.of(
                "status", 404,
                "error", "NOT_FOUND",
                "message", ex.getMessage()
        );

        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String,Object>> errorAdding(IOException ex){
        Map<String,Object> error = Map.of(
                "status", 500,
                "error", "INTERNAL_SERVER_ERROR",
                "message", ex.getMessage()
        );

        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
