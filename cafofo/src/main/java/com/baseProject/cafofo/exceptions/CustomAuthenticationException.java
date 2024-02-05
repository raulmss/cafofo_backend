package com.baseProject.cafofo.exceptions;

public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}