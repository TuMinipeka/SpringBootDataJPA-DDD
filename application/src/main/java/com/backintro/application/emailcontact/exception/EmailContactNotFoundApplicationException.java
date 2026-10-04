package com.backintro.application.emailcontact.exception;

import com.backintro.application.common.exception.ApplicationException;

public class EmailContactNotFoundApplicationException
        extends ApplicationException {

    public EmailContactNotFoundApplicationException(String message) {
        super(message);
    }
}
