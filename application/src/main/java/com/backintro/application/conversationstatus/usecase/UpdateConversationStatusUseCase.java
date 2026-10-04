package com.backintro.application.conversationstatus.usecase;

import com.backintro.application.conversationstatus.command.UpdateConversationStatusCommand;
import com.backintro.application.conversationstatus.dto.ConversationStatusResponse;
import com.backintro.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class UpdateConversationStatusUseCase {

    private final ConversationStatusRepository conversationStatusRepository;

    public UpdateConversationStatusUseCase(
            ConversationStatusRepository conversationStatusRepository
    ) {
        this.conversationStatusRepository = conversationStatusRepository;
    }

    public ConversationStatusResponse execute(
            UpdateConversationStatusCommand command
    ) {
        var conversationStatus = conversationStatusRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new ConversationStatusNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        conversationStatus.update(command.nameStatus());

        var updated = conversationStatusRepository.save(conversationStatus);

        return new ConversationStatusResponse(
                updated.id().value(),
                updated.nameStatus()
        );
    }
}
