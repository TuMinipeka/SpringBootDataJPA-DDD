package com.backintro.application.chatairunerror.usecase;

import com.backintro.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.backintro.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import com.backintro.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.backintro.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.backintro.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class GetChatAiRunErrorByIdUseCase {

    private final ChatAiRunErrorRepository errorRepository;

    public GetChatAiRunErrorByIdUseCase(
            ChatAiRunErrorRepository errorRepository
    ) {
        this.errorRepository = errorRepository;
    }

    public ChatAiRunErrorResponse execute(ChatAiRunErrorId id) {
        var error = errorRepository.findById(id)
                .orElseThrow(() ->
                        new ChatAiRunErrorNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(error);
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
