package com.backintro.application.conversationstatus.usecase;

import com.backintro.application.conversationstatus.dto.ConversationStatusResponse;
import com.backintro.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class GetConversationStatusByIdUseCase {

    private final ConversationStatusRepository conversationStatusRepository;

    public GetConversationStatusByIdUseCase(
            ConversationStatusRepository conversationStatusRepository
    ) {
        this.conversationStatusRepository = conversationStatusRepository;
    }

    public ConversationStatusResponse execute(ConversationStatusId id) {
        var conversationStatus = conversationStatusRepository.findById(id)
                .orElseThrow(() ->
                        new ConversationStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new ConversationStatusResponse(
                conversationStatus.id().value(),
                conversationStatus.nameStatus()
        );
    }
}
