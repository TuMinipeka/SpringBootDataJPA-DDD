package com.backintro.application.emailcontact.command;

import java.util.Objects;

import com.backintro.domain.contact.model.valueobject.ContactId;

public record RegisterEmailContactCommand(
        ContactId contactId,
        String email,
        String notes
) {

    public RegisterEmailContactCommand {
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(email, "email must not be null");
    }
}
