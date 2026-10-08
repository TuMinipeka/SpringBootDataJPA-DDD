package com.backintro.infrastructure.security.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import com.backintro.domain.security.exception.InvalidCredentialsException;
import com.backintro.domain.security.exception.SecurityDomainException;
import com.backintro.domain.security.exception.SecurityUserNotFoundException;

@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    ResponseEntity<SecurityErrorResponse> handleInvalidCredentials(
            InvalidCredentialsException exception,
            ServletWebRequest request
    ) {
        return response(HttpStatus.UNAUTHORIZED, exception.getMessage(), request);
    }

    @ExceptionHandler(SecurityUserNotFoundException.class)
    ResponseEntity<SecurityErrorResponse> handleUserNotFound(
            SecurityUserNotFoundException exception,
            ServletWebRequest request
    ) {
        return response(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    @ExceptionHandler(SecurityDomainException.class)
    ResponseEntity<SecurityErrorResponse> handleDomainException(
            SecurityDomainException exception,
            ServletWebRequest request
    ) {
        return response(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    private ResponseEntity<SecurityErrorResponse> response(
            HttpStatus status,
            String message,
            ServletWebRequest request
    ) {
        return ResponseEntity.status(status).body(
                new SecurityErrorResponse(
                        Instant.now(),
                        status.value(),
                        status.getReasonPhrase(),
                        message,
                        request.getRequest().getRequestURI()
                )
        );
    }
}
