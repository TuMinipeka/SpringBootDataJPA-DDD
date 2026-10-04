package com.backintro.domain.common.exception;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;

public class AiModelNotFoundException extends RuntimeException {

    public AiModelNotFoundException(AiModelId id) {
        super("AI model not found with id: " + id.value());
    }
}
