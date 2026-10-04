package com.backintro.application.chatairun.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.backintro.domain.chatairun.event.ChatAiRunDeletedEvent;
import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;

public class DeleteChatAiRunUseCase {

    private final ChatAiRunRepository chatAiRunRepository;

    public DeleteChatAiRunUseCase(ChatAiRunRepository chatAiRunRepository) {
        this.chatAiRunRepository = chatAiRunRepository;
    }

    public ChatAiRunDeletedEvent execute(ChatAiRunId id) {
        var chatAiRun = chatAiRunRepository.findById(id)
                .orElseThrow(() ->
                        new ChatAiRunNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        chatAiRunRepository.delete(chatAiRun);

        return new ChatAiRunDeletedEvent(id, LocalDateTime.now());
    }
}
