package com.backintro.domain.common.exception;

import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;

public class AiRunStatusNotFoundException extends RuntimeException {

    public AiRunStatusNotFoundException(AiRunStatusId id) {
        super("AI run status not found with id: " + id.value());
    }
}
