package com.twuc.shopping.api;

import com.twuc.shopping.dto.ErrorMessage;
import com.twuc.shopping.exception.ProductAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventsHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> productAlreadyExistsExceptionHandler(ProductAlreadyExistsException ex) {
        ErrorMessage errorMessage = new ErrorMessage("product already exists");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }
}
