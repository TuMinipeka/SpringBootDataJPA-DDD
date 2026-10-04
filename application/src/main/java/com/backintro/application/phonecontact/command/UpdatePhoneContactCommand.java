package com.backintro.application.phonecontact.command;

import java.util.Objects;

import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;

public record UpdatePhoneContactCommand(
        PhoneContactId id,
        String phone,
        String notes
) {

    public UpdatePhoneContactCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(phone, "phone must not be null");
    }
}
