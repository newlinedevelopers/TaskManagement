package com.example.TaskManagement.RequestExceptionHandler;

import org.springframework.security.core.AuthenticationException;

public class LoginAuthenticationException extends AuthenticationException{
    private final String errorMessage;
    public LoginAuthenticationException(String errorMessage) {
        super(errorMessage);
        System.out.println(errorMessage);
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
