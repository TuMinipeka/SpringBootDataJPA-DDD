package com.backintro.domain.chatairun.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatAiRunUpdatedEvent(
        ChatAiRunId id,
        ChatMessageId messageId,
        AiModelId modelId,
        AiRunStatusId aiRunStatusId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatAiRunUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(modelId, "modelId must not be null");
        Objects.requireNonNull(aiRunStatusId, "aiRunStatusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
