package com.backintro.application.chatairunerror.usecase;

import com.backintro.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.backintro.application.chatairunerror.command.RegisterChatAiRunErrorCommand;
import com.backintro.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;
import com.backintro.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.backintro.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class RegisterChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository errorRepository;
    private final ChatAiRunRepository chatAiRunRepository;

    public RegisterChatAiRunErrorUseCase(
            ChatAiRunErrorRepository errorRepository,
            ChatAiRunRepository chatAiRunRepository
    ) {
        this.errorRepository = errorRepository;
        this.chatAiRunRepository = chatAiRunRepository;
    }

    public ChatAiRunErrorResponse execute(
            RegisterChatAiRunErrorCommand command
    ) {
        chatAiRunRepository.findById(command.aiRunId())
                .orElseThrow(() ->
                        new ChatAiRunNotFoundApplicationException(
                                command.aiRunId().value().toString()
                        )
                );

        ChatAiRunError error = ChatAiRunError.register(
                command.aiRunId(),
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
