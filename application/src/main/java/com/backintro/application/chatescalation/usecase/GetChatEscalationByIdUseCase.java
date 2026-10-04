package com.backintro.application.chatescalation.usecase;

import com.backintro.application.chatescalation.dto.ChatEscalationResponse;
import com.backintro.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.backintro.domain.chatescalation.model.aggregate.ChatEscalation;
import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;

public class GetChatEscalationByIdUseCase {

    private final ChatEscalationRepository escalationRepository;

    public GetChatEscalationByIdUseCase(
            ChatEscalationRepository escalationRepository
    ) {
        this.escalationRepository = escalationRepository;
    }

    public ChatEscalationResponse execute(ChatEscalationId id) {
        var escalation = escalationRepository.findById(id)
                .orElseThrow(() ->
                        new ChatEscalationNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(escalation);
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
