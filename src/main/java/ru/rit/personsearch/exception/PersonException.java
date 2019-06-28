package ru.rit.personsearch.exception;

public class PersonException extends RuntimeException {
    private final String params;

    public PersonException(String message) {
        this(message, null, null);
    }

    public PersonException(String message, String params) {
        super(message);
        this.params = params;
    }

    public PersonException(String message, Exception e) {
        this(message, null, e);
    }

    public PersonException(String message, String params, Exception e) {
        super(message, e);
        this.params = params;
    }

    public PersonException(Exception e) {
        this(e.getMessage(), e);
    }

    public String getParams() {
        return params;
    }
}
