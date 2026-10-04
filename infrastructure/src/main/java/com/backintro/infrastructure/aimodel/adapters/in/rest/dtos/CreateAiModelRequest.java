package com.backintro.infrastructure.aimodel.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateAiModelRequest(

        @NotNull(message = "providerModelId is required")
        UUID providerModelId,

        @NotBlank(message = "nameModel is required")
        @Size(max = 100, message = "nameModel must have at most 100 characters")
        String nameModel,

        @NotBlank(message = "modelKey is required")
        @Size(max = 120, message = "modelKey must have at most 120 characters")
        String modelKey,

        @NotNull(message = "inputTokenPrice is required")
        @DecimalMin(
                value = "0.0",
                message = "inputTokenPrice must be greater than or equal to zero"
        )
        BigDecimal inputTokenPrice,

        @NotNull(message = "outputTokenPrice is required")
        @DecimalMin(
                value = "0.0",
                message = "outputTokenPrice must be greater than or equal to zero"
        )
        BigDecimal outputTokenPrice,

        @PositiveOrZero(
                message = "maxTokens must be greater than or equal to zero"
        )
        Integer maxTokens,

        @PositiveOrZero(
                message = "contextWindow must be greater than or equal to zero"
        )
        Integer contextWindow

) {
}
