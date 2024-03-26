package ru.minsafin.securityjwt.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.minsafin.securityjwt.dto.EntityErrorResponse;
import ru.minsafin.securityjwt.exceptions.TopicNotCreatedException;
import ru.minsafin.securityjwt.exceptions.TopicNotFoundException;
import ru.minsafin.securityjwt.exceptions.UserNotCreatedException;

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

    @ExceptionHandler(ExpiredJwtException.class)
    private ResponseEntity<EntityErrorResponse> handleException(ExpiredJwtException e) {
        EntityErrorResponse response = new EntityErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
