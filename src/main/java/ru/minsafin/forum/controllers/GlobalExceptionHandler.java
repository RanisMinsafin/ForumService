package ru.minsafin.forum.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.minsafin.forum.dto.EntityErrorResponse;
import ru.minsafin.forum.exceptions.MessageNotFoundException;
import ru.minsafin.forum.exceptions.TopicNotCreatedException;
import ru.minsafin.forum.exceptions.TopicNotFoundException;
import ru.minsafin.forum.exceptions.UserNotCreatedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotCreatedException.class)
    private ResponseEntity<EntityErrorResponse> handleException(UserNotCreatedException e) {
        EntityErrorResponse response = new EntityErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TopicNotCreatedException.class)
    private ResponseEntity<EntityErrorResponse> handleException(TopicNotCreatedException e) {
        EntityErrorResponse response = new EntityErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TopicNotFoundException.class)
    private ResponseEntity<EntityErrorResponse> handleException(TopicNotFoundException e) {
        EntityErrorResponse response = new EntityErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    private ResponseEntity<EntityErrorResponse> handleException(MessageNotFoundException e) {
        EntityErrorResponse response = new EntityErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
