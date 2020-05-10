package com.github.snkotv.communication.exceptions;

public class InvalidUserNameException extends Exception {
    private String message;

    public InvalidUserNameException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
