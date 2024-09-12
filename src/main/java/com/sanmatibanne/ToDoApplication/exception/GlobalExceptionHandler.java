package com.sanmatibanne.ToDoApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(ToDoApiException.class)
    public ResponseEntity<ErrorDetails> handleToDoException(
            ToDoApiException toDoApiException, WebRequest webRequest){

        ErrorDetails errorDetails=new ErrorDetails(
        LocalDateTime.now(),
        toDoApiException.getMessage(),
        webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
