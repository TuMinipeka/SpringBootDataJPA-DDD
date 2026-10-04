package com.backintro.application.conversationstatus.usecase;

import java.util.List;

import com.backintro.application.conversationstatus.dto.ConversationStatusResponse;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class ListConversationStatusUseCase {

    private final ConversationStatusRepository conversationStatusRepository;

    public ListConversationStatusUseCase(
            ConversationStatusRepository conversationStatusRepository
    ) {
        this.conversationStatusRepository = conversationStatusRepository;
    }

    public List<ConversationStatusResponse> execute() {
        return conversationStatusRepository.findAll()
                .stream()
                .map(conversationStatus ->
                        new ConversationStatusResponse(
                                conversationStatus.id().value(),
                                conversationStatus.nameStatus()
                        )
                )
                .toList();
    }
}
