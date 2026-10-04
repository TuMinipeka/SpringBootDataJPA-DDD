package com.backintro.application.relationshiptype.exception;

import com.backintro.application.common.exception.ApplicationException;

public class RelationshipTypeNotFoundApplicationException
        extends ApplicationException {

    public RelationshipTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
