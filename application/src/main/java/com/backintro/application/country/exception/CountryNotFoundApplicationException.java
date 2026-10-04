package com.backintro.application.country.exception;

import com.backintro.application.common.exception.ApplicationException;

public class CountryNotFoundApplicationException
        extends ApplicationException {

    public CountryNotFoundApplicationException(String message) {
        super(message);
    }
}
