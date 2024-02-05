package com.baseProject.baseProject.exceptions;

public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}