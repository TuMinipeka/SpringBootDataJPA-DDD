package com.backintro.application.aimodel.command;

import java.math.BigDecimal;
import java.util.Objects;

import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;

public record RegisterAiModelCommand(
        AiProviderModelId providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow
) {

    public RegisterAiModelCommand {
        Objects.requireNonNull(
                providerModelId,
                "providerModelId must not be null"
        );
        Objects.requireNonNull(nameModel, "nameModel must not be null");
        Objects.requireNonNull(modelKey, "modelKey must not be null");
        Objects.requireNonNull(
                inputTokenPrice,
                "inputTokenPrice must not be null"
        );
        Objects.requireNonNull(
                outputTokenPrice,
                "outputTokenPrice must not be null"
        );
    }
}
