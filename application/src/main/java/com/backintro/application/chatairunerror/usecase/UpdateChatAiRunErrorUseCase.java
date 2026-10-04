package com.backintro.application.chatairunerror.usecase;

import com.backintro.application.chatairunerror.command.UpdateChatAiRunErrorCommand;
import com.backintro.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.backintro.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import com.backintro.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.backintro.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class UpdateChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository errorRepository;

    public UpdateChatAiRunErrorUseCase(
            ChatAiRunErrorRepository errorRepository
    ) {
        this.errorRepository = errorRepository;
    }

    public ChatAiRunErrorResponse execute(
            UpdateChatAiRunErrorCommand command
    ) {
        var error = errorRepository.findById(command.id())
                .orElseThrow(() ->
                        new ChatAiRunErrorNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        error.update(
                command.errorMessage(),
                command.errorCode(),
                command.providerErrorId()
        );

        return toResponse(errorRepository.save(error));
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
