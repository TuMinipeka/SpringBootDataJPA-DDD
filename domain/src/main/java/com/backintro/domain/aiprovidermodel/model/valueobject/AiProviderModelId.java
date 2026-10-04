package com.backintro.domain.aiprovidermodel.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record AiProviderModelId(UUID value) {

    public AiProviderModelId {
        Objects.requireNonNull(
                value,
                "AiProviderModelId value must not be null"
        );
    }

    public static AiProviderModelId generate() {
        return new AiProviderModelId(UUID.randomUUID());
    }
}
