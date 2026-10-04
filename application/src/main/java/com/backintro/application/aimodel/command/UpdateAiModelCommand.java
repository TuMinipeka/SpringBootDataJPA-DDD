package com.backintro.application.aimodel.command;

import java.math.BigDecimal;
import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;

public record UpdateAiModelCommand(
        AiModelId id,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow
) {

    public UpdateAiModelCommand {
        Objects.requireNonNull(id, "id must not be null");
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
