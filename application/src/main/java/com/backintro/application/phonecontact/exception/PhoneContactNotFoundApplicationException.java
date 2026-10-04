package com.backintro.application.phonecontact.exception;

import com.backintro.application.common.exception.ApplicationException;

public class PhoneContactNotFoundApplicationException
        extends ApplicationException {

    public PhoneContactNotFoundApplicationException(String message) {
        super(message);
    }
}
