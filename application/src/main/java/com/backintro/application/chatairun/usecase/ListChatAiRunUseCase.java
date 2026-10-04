package com.backintro.application.chatairun.usecase;

import java.util.List;

import com.backintro.application.chatairun.dto.ChatAiRunResponse;
import com.backintro.domain.chatairun.model.aggregate.ChatAiRun;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;

public class ListChatAiRunUseCase {

    private final ChatAiRunRepository chatAiRunRepository;

    public ListChatAiRunUseCase(ChatAiRunRepository chatAiRunRepository) {
        this.chatAiRunRepository = chatAiRunRepository;
    }

    public List<ChatAiRunResponse> execute() {
        return chatAiRunRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
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
