package com.backintro.domain.common.exception;

import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class EscalationStatusNotFoundException extends RuntimeException {

    public EscalationStatusNotFoundException(EscalationStatusId id) {
        super("Escalation status not found with id: " + id.value());
    }
}
