package com.backintro.application.chatescalation.usecase;

import com.backintro.application.chatescalation.command.UpdateChatEscalationCommand;
import com.backintro.application.chatescalation.dto.ChatEscalationResponse;
import com.backintro.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.backintro.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.backintro.domain.chatescalation.model.aggregate.ChatEscalation;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class UpdateChatEscalationUseCase {

    private final ChatEscalationRepository escalationRepository;
    private final EscalationStatusRepository statusRepository;

    public UpdateChatEscalationUseCase(
            ChatEscalationRepository escalationRepository,
            EscalationStatusRepository statusRepository
    ) {
        this.escalationRepository = escalationRepository;
        this.statusRepository = statusRepository;
    }

    public ChatEscalationResponse execute(
            UpdateChatEscalationCommand command
    ) {
        var escalation = escalationRepository.findById(command.id())
                .orElseThrow(() ->
                        new ChatEscalationNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new EscalationStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        escalation.update(
                command.statusId(),
                command.fromAi(),
                command.reason()
        );

        return toResponse(escalationRepository.save(escalation));
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
