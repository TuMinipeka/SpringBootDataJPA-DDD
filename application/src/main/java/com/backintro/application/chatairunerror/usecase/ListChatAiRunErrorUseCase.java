package com.backintro.application.chatairunerror.usecase;

import java.util.List;

import com.backintro.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.backintro.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.backintro.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class ListChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository errorRepository;

    public ListChatAiRunErrorUseCase(
            ChatAiRunErrorRepository errorRepository
    ) {
        this.errorRepository = errorRepository;
    }

    public List<ChatAiRunErrorResponse> execute() {
        return errorRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ChatAiRunErrorResponse toResponse(ChatAiRunError error) {
        return new ChatAiRunErrorResponse(
                error.id().value(),
                error.aiRunId().value(),
                error.errorMessage(),
                error.errorCode(),
                error.providerErrorId()
        );
    }
}
