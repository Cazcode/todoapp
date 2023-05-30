package com.cazcode.todoapp.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ToDoExceptions extends RuntimeException{

    private final String message;
    private final HttpStatus httpStatus;

    public ToDoExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
