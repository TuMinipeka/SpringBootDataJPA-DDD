package com.backintro.domain.security.exception;

public final class InvalidCredentialsException extends SecurityDomainException {

    public InvalidCredentialsException() {
        super("Invalid credentials");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
