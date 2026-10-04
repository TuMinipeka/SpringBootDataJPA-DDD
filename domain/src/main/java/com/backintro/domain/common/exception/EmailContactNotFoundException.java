package com.backintro.domain.common.exception;

import com.backintro.domain.emailcontact.model.valueobject.EmailContactId;

public class EmailContactNotFoundException extends RuntimeException {

    public EmailContactNotFoundException(EmailContactId id) {
        super("Email contact not found with id: " + id.value());
    }
}
