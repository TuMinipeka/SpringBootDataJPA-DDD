package com.backintro.infrastructure.aiprovidermodel.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateAiProviderModelRequest(

        @NotBlank(message = "nameProviderAi is required")
        @Size(max = 100, message = "nameProviderAi must have at most 100 characters")
        String nameProviderAi,

        @Size(max = 100, message = "razonSocial must have at most 100 characters")
        String razonSocial,

        String sitioWeb

) {
}
