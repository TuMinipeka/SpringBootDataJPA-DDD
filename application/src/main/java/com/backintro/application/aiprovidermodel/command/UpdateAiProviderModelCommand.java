package com.backintro.application.aiprovidermodel.command;

import java.util.Objects;

import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;

public record UpdateAiProviderModelCommand(
        AiProviderModelId id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb
) {

    public UpdateAiProviderModelCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameProviderAi, "nameProviderAi must not be null");
    }
}
