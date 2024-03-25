package ru.minsafin.securityjwt.utils;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException(String message) {
        super(message);
    }
}
