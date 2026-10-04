package com.backintro.domain.common.exception;

import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;

public class AiProviderModelNotFoundException extends RuntimeException {

    public AiProviderModelNotFoundException(AiProviderModelId id) {
        super("AI provider model not found with id: " + id.value());
    }
}
