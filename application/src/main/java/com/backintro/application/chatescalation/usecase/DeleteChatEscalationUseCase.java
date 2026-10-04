package com.backintro.application.chatescalation.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.backintro.domain.chatescalation.event.ChatEscalationDeletedEvent;
import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;

public class DeleteChatEscalationUseCase {

    private final ChatEscalationRepository escalationRepository;

    public DeleteChatEscalationUseCase(
            ChatEscalationRepository escalationRepository
    ) {
        this.escalationRepository = escalationRepository;
    }

    public ChatEscalationDeletedEvent execute(ChatEscalationId id) {
        var escalation = escalationRepository.findById(id)
                .orElseThrow(() ->
                        new ChatEscalationNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        escalationRepository.delete(escalation);

        return new ChatEscalationDeletedEvent(id, LocalDateTime.now());
    }
}
