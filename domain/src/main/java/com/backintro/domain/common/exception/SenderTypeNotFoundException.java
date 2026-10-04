package com.backintro.domain.common.exception;

import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;

public class SenderTypeNotFoundException extends RuntimeException {

    public SenderTypeNotFoundException(SenderTypeId id) {
        super("Sender type not found with id: " + id.value());
    }
}
