package com.backintro.application.conversationstatus.usecase;

import java.time.LocalDateTime;

import com.backintro.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.backintro.domain.conversationstatus.event.ConversationStatusDeletedEvent;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class DeleteConversationStatusUseCase {

    private final ConversationStatusRepository conversationStatusRepository;

    public DeleteConversationStatusUseCase(
            ConversationStatusRepository conversationStatusRepository
    ) {
        this.conversationStatusRepository = conversationStatusRepository;
    }

    public ConversationStatusDeletedEvent execute(ConversationStatusId id) {
        var conversationStatus = conversationStatusRepository.findById(id)
                .orElseThrow(() ->
                        new ConversationStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        conversationStatusRepository.delete(conversationStatus);

        return new ConversationStatusDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
