package com.backintro.application.chatescalation.usecase;

import java.util.List;

import com.backintro.application.chatescalation.dto.ChatEscalationResponse;
import com.backintro.domain.chatescalation.model.aggregate.ChatEscalation;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;

public class ListChatEscalationUseCase {

    private final ChatEscalationRepository escalationRepository;

    public ListChatEscalationUseCase(
            ChatEscalationRepository escalationRepository
    ) {
        this.escalationRepository = escalationRepository;
    }

    public List<ChatEscalationResponse> execute() {
        return escalationRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ChatEscalationResponse toResponse(ChatEscalation escalation) {
        return new ChatEscalationResponse(
                escalation.id().value(),
                escalation.conversationId().value(),
                escalation.statusId().value(),
                escalation.fromAi(),
                escalation.reason()
        );
    }
}
