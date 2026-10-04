package com.backintro.application.chatairunmetric.command;

import java.math.BigDecimal;
import java.util.Objects;

import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public record UpdateChatAiRunMetricCommand(
        ChatAiRunMetricId id,
        int promptTokens,
        int completionTokens,
        int totalTokens,
        BigDecimal cost
) {

    public UpdateChatAiRunMetricCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(cost, "cost must not be null");
    }
}
