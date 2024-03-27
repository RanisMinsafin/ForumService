package ru.minsafin.forum.exceptions;

public class TopicNotCreatedException extends RuntimeException {
    public TopicNotCreatedException(String message) {
        super(message);
    }
}
