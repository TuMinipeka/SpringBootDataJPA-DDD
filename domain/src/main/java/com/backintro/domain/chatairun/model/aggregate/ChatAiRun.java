package com.backintro.domain.chatairun.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.chatairun.event.ChatAiRunRegisteredEvent;
import com.backintro.domain.chatairun.event.ChatAiRunUpdatedEvent;
import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.common.model.AggregateRoot;

public class ChatAiRun extends AggregateRoot {

    private final ChatAiRunId id;
    private final ChatConversationId conversationId;
    private ChatMessageId messageId;
    private AiModelId modelId;
    private AiRunStatusId aiRunStatusId;

    private ChatAiRun(
            ChatAiRunId id,
            ChatConversationId conversationId,
            ChatMessageId messageId,
            AiModelId modelId,
            AiRunStatusId aiRunStatusId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
        this.messageId = messageId;
        this.modelId = Objects.requireNonNull(
                modelId,
                "modelId must not be null"
        );
        this.aiRunStatusId = Objects.requireNonNull(
                aiRunStatusId,
                "aiRunStatusId must not be null"
        );
    }

    public static ChatAiRun register(
            ChatConversationId conversationId,
            ChatMessageId messageId,
            AiModelId modelId,
            AiRunStatusId aiRunStatusId
    ) {
        ChatAiRunId id = ChatAiRunId.generate();
        ChatAiRun chatAiRun = new ChatAiRun(
                id,
                conversationId,
                messageId,
                modelId,
                aiRunStatusId
        );

        chatAiRun.recordEvent(
                new ChatAiRunRegisteredEvent(id, LocalDateTime.now())
        );

        return chatAiRun;
    }

    public static ChatAiRun restore(
            ChatAiRunId id,
            ChatConversationId conversationId,
            ChatMessageId messageId,
            AiModelId modelId,
            AiRunStatusId aiRunStatusId
    ) {
        return new ChatAiRun(
                id,
                conversationId,
                messageId,
                modelId,
                aiRunStatusId
        );
    }

    public void update(
            ChatMessageId messageId,
            AiModelId modelId,
            AiRunStatusId aiRunStatusId
    ) {
        this.messageId = messageId;
        this.modelId = Objects.requireNonNull(
                modelId,
                "modelId must not be null"
        );
        this.aiRunStatusId = Objects.requireNonNull(
                aiRunStatusId,
                "aiRunStatusId must not be null"
        );

        recordEvent(
                new ChatAiRunUpdatedEvent(
                        this.id,
                        this.messageId,
                        this.modelId,
                        this.aiRunStatusId,
                        LocalDateTime.now()
                )
        );
    }

    public ChatAiRunId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public ChatMessageId messageId() {
        return messageId;
    }

    public AiModelId modelId() {
        return modelId;
    }

    public AiRunStatusId aiRunStatusId() {
        return aiRunStatusId;
    }
}
