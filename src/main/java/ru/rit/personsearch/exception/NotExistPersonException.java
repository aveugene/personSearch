package ru.rit.personsearch.exception;

public class NotExistPersonException extends PersonException {
    public NotExistPersonException(String params) {
        super("Person " + params + " not exist.", params);
    }
}
