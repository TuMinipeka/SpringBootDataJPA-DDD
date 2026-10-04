package com.backintro.application.conversationstatus.usecase;

import com.backintro.application.conversationstatus.command.RegisterConversationStatusCommand;
import com.backintro.application.conversationstatus.dto.ConversationStatusResponse;
import com.backintro.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class RegisterConversationStatusUseCase {

    private final ConversationStatusRepository conversationStatusRepository;

    public RegisterConversationStatusUseCase(
            ConversationStatusRepository conversationStatusRepository
    ) {
        this.conversationStatusRepository = conversationStatusRepository;
    }

    public ConversationStatusResponse execute(
            RegisterConversationStatusCommand command
    ) {
        ConversationStatus conversationStatus =
                ConversationStatus.register(command.nameStatus());
        ConversationStatus saved =
                conversationStatusRepository.save(conversationStatus);

        return new ConversationStatusResponse(
                saved.id().value(),
                saved.nameStatus()
        );
    }
}
