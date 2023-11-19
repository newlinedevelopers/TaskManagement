package com.example.TaskManagement.RequestExceptionHandler;

import com.example.TaskManagement.Payload.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateUserExceptionHandler.class)
    public ResponseEntity<Object> handleDuplicateEmailException(DuplicateUserExceptionHandler ex) {
        return ResponseEntity.badRequest().body(new MessageResponse(ex.getErrorMessage()));
    }

    @ExceptionHandler(LoginAuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(LoginAuthenticationException ex) {
        return ResponseEntity.badRequest().body(new MessageResponse(ex.getErrorMessage()));
    }
}
