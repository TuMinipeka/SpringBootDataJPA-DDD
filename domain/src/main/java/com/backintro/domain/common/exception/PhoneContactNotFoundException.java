package com.backintro.domain.common.exception;

import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;

public class PhoneContactNotFoundException extends RuntimeException {

    public PhoneContactNotFoundException(PhoneContactId id) {
        super("Phone contact not found with id: " + id.value());
    }
}
