package com.backintro.application.aimodel.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AiModelResponse(
        UUID id,
        UUID providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow
) {
}
