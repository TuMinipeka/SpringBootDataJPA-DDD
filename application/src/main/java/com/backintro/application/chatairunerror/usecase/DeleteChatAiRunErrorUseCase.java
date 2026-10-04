package com.backintro.application.chatairunerror.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import com.backintro.domain.chatairunerror.event.ChatAiRunErrorDeletedEvent;
import com.backintro.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.backintro.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class DeleteChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository errorRepository;

    public DeleteChatAiRunErrorUseCase(
            ChatAiRunErrorRepository errorRepository
    ) {
        this.errorRepository = errorRepository;
    }

    public ChatAiRunErrorDeletedEvent execute(ChatAiRunErrorId id) {
        var error = errorRepository.findById(id)
                .orElseThrow(() ->
                        new ChatAiRunErrorNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        errorRepository.delete(error);

        return new ChatAiRunErrorDeletedEvent(id, LocalDateTime.now());
    }
}
