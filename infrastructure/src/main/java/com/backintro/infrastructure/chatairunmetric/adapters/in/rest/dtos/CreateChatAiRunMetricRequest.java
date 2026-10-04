package com.backintro.infrastructure.chatairunmetric.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateChatAiRunMetricRequest(

        @NotNull(message = "aiRunId is required")
        UUID aiRunId,

        @PositiveOrZero(
                message = "promptTokens must be greater than or equal to zero"
        )
        int promptTokens,

        @PositiveOrZero(
                message = "completionTokens must be greater than or equal to zero"
        )
        int completionTokens,

        @PositiveOrZero(
                message = "totalTokens must be greater than or equal to zero"
        )
        int totalTokens,

        @NotNull(message = "cost is required")
        @DecimalMin(
                value = "0.0",
                message = "cost must be greater than or equal to zero"
        )
        BigDecimal cost

) {
}
