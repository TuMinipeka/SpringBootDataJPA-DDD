package com.backintro.application.contact.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ContactNotFoundApplicationException extends ApplicationException {

    public ContactNotFoundApplicationException(String message) {
        super(message);
    }
}
