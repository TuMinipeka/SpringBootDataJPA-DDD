package com.backintro.application.aiprovidermodel.dto;

import java.util.UUID;

public record AiProviderModelResponse(
        UUID id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb
) {
}
