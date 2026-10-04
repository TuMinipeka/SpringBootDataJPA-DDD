package com.backintro.application.chatairunmetric.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ChatAiRunMetricResponse(
        UUID id,
        UUID aiRunId,
        int promptTokens,
        int completionTokens,
        int totalTokens,
        BigDecimal cost
) {
}
