package com.backintro.application.chatairun.usecase;

import com.backintro.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.backintro.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.backintro.application.chatairun.command.UpdateChatAiRunCommand;
import com.backintro.application.chatairun.dto.ChatAiRunResponse;
import com.backintro.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.backintro.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;
import com.backintro.domain.chatairun.model.aggregate.ChatAiRun;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;

public class UpdateChatAiRunUseCase {

    private final ChatAiRunRepository chatAiRunRepository;
    private final ChatMessageRepository messageRepository;
    private final AiModelRepository aiModelRepository;
    private final AiRunStatusRepository aiRunStatusRepository;

    public UpdateChatAiRunUseCase(
            ChatAiRunRepository chatAiRunRepository,
            ChatMessageRepository messageRepository,
            AiModelRepository aiModelRepository,
            AiRunStatusRepository aiRunStatusRepository
    ) {
        this.chatAiRunRepository = chatAiRunRepository;
        this.messageRepository = messageRepository;
        this.aiModelRepository = aiModelRepository;
        this.aiRunStatusRepository = aiRunStatusRepository;
    }

    public ChatAiRunResponse execute(UpdateChatAiRunCommand command) {
        var chatAiRun = chatAiRunRepository.findById(command.id())
                .orElseThrow(() ->
                        new ChatAiRunNotFoundApplicationException(
                                command.id().value().toString()
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

        chatAiRun.update(
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
