package com.backintro.application.documenttype.exception;

import com.backintro.application.common.exception.ApplicationException;

public class DocumentTypeNotFoundApplicationException
        extends ApplicationException {

    public DocumentTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
