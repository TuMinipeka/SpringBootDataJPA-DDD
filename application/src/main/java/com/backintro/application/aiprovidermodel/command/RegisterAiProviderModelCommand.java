package com.backintro.application.aiprovidermodel.command;

import java.util.Objects;

public record RegisterAiProviderModelCommand(
        String nameProviderAi,
        String razonSocial,
        String sitioWeb
) {

    public RegisterAiProviderModelCommand {
        Objects.requireNonNull(nameProviderAi, "nameProviderAi must not be null");
    }
}
