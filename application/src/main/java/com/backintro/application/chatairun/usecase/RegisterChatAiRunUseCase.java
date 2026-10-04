package com.backintro.application.chatairun.usecase;

import com.backintro.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.backintro.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.backintro.application.chatairun.command.RegisterChatAiRunCommand;
import com.backintro.application.chatairun.dto.ChatAiRunResponse;
import com.backintro.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.backintro.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;
import com.backintro.domain.chatairun.model.aggregate.ChatAiRun;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;

public class RegisterChatAiRunUseCase {

    private final ChatAiRunRepository chatAiRunRepository;
    private final ChatConversationRepository conversationRepository;
    private final ChatMessageRepository messageRepository;
    private final AiModelRepository aiModelRepository;
    private final AiRunStatusRepository aiRunStatusRepository;

    public RegisterChatAiRunUseCase(
            ChatAiRunRepository chatAiRunRepository,
            ChatConversationRepository conversationRepository,
            ChatMessageRepository messageRepository,
            AiModelRepository aiModelRepository,
            AiRunStatusRepository aiRunStatusRepository
    ) {
        this.chatAiRunRepository = chatAiRunRepository;
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.aiModelRepository = aiModelRepository;
        this.aiRunStatusRepository = aiRunStatusRepository;
    }

    public ChatAiRunResponse execute(RegisterChatAiRunCommand command) {
        conversationRepository.findById(command.conversationId())
                .orElseThrow(() ->
                        new ChatConversationNotFoundApplicationException(
                                command.conversationId().value().toString()
                        )
                );
        validateMessage(command.messageId());
        aiModelRepository.findById(command.modelId())
                .orElseThrow(() ->
                        new AiModelNotFoundApplicationException(
                                command.modelId().value().toString()
                        )
                );
        aiRunStatusRepository.findById(command.aiRunStatusId())
                .orElseThrow(() ->
                        new AiRunStatusNotFoundApplicationException(
                                command.aiRunStatusId().value().toString()
                        )
                );

        ChatAiRun chatAiRun = ChatAiRun.register(
                command.conversationId(),
                command.messageId(),
                command.modelId(),
                command.aiRunStatusId()
        );

        return toResponse(chatAiRunRepository.save(chatAiRun));
    }

    private void validateMessage(ChatMessageId messageId) {
        if (messageId != null) {
            messageRepository.findById(messageId)
                    .orElseThrow(() ->
                            new ChatMessageNotFoundApplicationException(
                                    messageId.value().toString()
                            )
                    );
        }
    }

    private ChatAiRunResponse toResponse(ChatAiRun chatAiRun) {
        return new ChatAiRunResponse(
                chatAiRun.id().value(),
                chatAiRun.conversationId().value(),
                chatAiRun.messageId() == null
                        ? null
                        : chatAiRun.messageId().value(),
                chatAiRun.modelId().value(),
                chatAiRun.aiRunStatusId().value()
        );
    }
}
