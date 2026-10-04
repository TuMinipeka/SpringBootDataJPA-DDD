package com.backintro.domain.chatairunerror.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunerror.event.ChatAiRunErrorRegisteredEvent;
import com.backintro.domain.chatairunerror.event.ChatAiRunErrorUpdatedEvent;
import com.backintro.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.backintro.domain.common.model.AggregateRoot;

public class ChatAiRunError extends AggregateRoot {

    private final ChatAiRunErrorId id;
    private final ChatAiRunId aiRunId;
    private String errorMessage;
    private String errorCode;
    private String providerErrorId;

    private ChatAiRunError(
            ChatAiRunErrorId id,
            ChatAiRunId aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.aiRunId = Objects.requireNonNull(
                aiRunId,
                "aiRunId must not be null"
        );
        this.errorMessage = Objects.requireNonNull(
                errorMessage,
                "errorMessage must not be null"
        );
        this.errorCode = errorCode;
        this.providerErrorId = providerErrorId;
    }

    public static ChatAiRunError register(
            ChatAiRunId aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId
    ) {
        ChatAiRunErrorId id = ChatAiRunErrorId.generate();
        ChatAiRunError error = new ChatAiRunError(
                id,
                aiRunId,
                errorMessage,
                errorCode,
                providerErrorId
        );

        error.recordEvent(
                new ChatAiRunErrorRegisteredEvent(id, LocalDateTime.now())
        );

        return error;
    }

    public static ChatAiRunError restore(
            ChatAiRunErrorId id,
            ChatAiRunId aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId
    ) {
        return new ChatAiRunError(
                id,
                aiRunId,
                errorMessage,
                errorCode,
                providerErrorId
        );
    }

    public void update(
            String errorMessage,
            String errorCode,
            String providerErrorId
    ) {
        this.errorMessage = Objects.requireNonNull(
                errorMessage,
                "errorMessage must not be null"
        );
        this.errorCode = errorCode;
        this.providerErrorId = providerErrorId;

        recordEvent(
                new ChatAiRunErrorUpdatedEvent(
                        this.id,
                        this.errorMessage,
                        this.errorCode,
                        this.providerErrorId,
                        LocalDateTime.now()
                )
        );
    }

    public ChatAiRunErrorId id() {
        return id;
    }

    public ChatAiRunId aiRunId() {
        return aiRunId;
    }

    public String errorMessage() {
        return errorMessage;
    }

    public String errorCode() {
        return errorCode;
    }

    public String providerErrorId() {
        return providerErrorId;
    }
}
