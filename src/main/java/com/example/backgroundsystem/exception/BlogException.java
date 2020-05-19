package com.example.backgroundsystem.exception;

public class BlogException extends MyException {
    BlogException(String message) {
        super(message);
    }

    public BlogException(String message, int statusCode) {
        super(message, statusCode);
    }

}
