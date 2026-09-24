package com.example.backend_1.exception;

public class UserDoesntExistException extends Exception {
    public UserDoesntExistException(String message) {
        super(message);
    }
}
