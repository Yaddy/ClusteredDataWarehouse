package com.anyankah.clusteredDataWarehouse.controller;

import com.anyankah.clusteredDataWarehouse.exceptions.NotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class FxDealGlobalErrorHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleEx (Exception ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setResponseCode(HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleEx (ValidationException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST);
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEx (NotFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        log.error(ex.getMessage());
        response.setResponseCode(HttpStatus.NOT_FOUND);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }
}
