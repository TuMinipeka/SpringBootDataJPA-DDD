package com.backintro.application.chatairun.usecase;

import com.backintro.application.chatairun.dto.ChatAiRunResponse;
import com.backintro.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.backintro.domain.chatairun.model.aggregate.ChatAiRun;
import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;

public class GetChatAiRunByIdUseCase {

    private final ChatAiRunRepository chatAiRunRepository;

    public GetChatAiRunByIdUseCase(
            ChatAiRunRepository chatAiRunRepository
    ) {
        this.chatAiRunRepository = chatAiRunRepository;
    }

    public ChatAiRunResponse execute(ChatAiRunId id) {
        var chatAiRun = chatAiRunRepository.findById(id)
                .orElseThrow(() ->
                        new ChatAiRunNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(chatAiRun);
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
