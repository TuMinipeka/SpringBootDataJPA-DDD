package com.backintro.application.chatescalation.usecase;

import com.backintro.application.chatescalation.command.RegisterChatEscalationCommand;
import com.backintro.application.chatescalation.dto.ChatEscalationResponse;
import com.backintro.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.backintro.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.backintro.domain.chatescalation.model.aggregate.ChatEscalation;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class RegisterChatEscalationUseCase {

    private final ChatEscalationRepository escalationRepository;
    private final ChatConversationRepository conversationRepository;
    private final EscalationStatusRepository statusRepository;

    public RegisterChatEscalationUseCase(
            ChatEscalationRepository escalationRepository,
            ChatConversationRepository conversationRepository,
            EscalationStatusRepository statusRepository
    ) {
        this.escalationRepository = escalationRepository;
        this.conversationRepository = conversationRepository;
        this.statusRepository = statusRepository;
    }

    public ChatEscalationResponse execute(
            RegisterChatEscalationCommand command
    ) {
        conversationRepository.findById(command.conversationId())
                .orElseThrow(() ->
                        new ChatConversationNotFoundApplicationException(
                                command.conversationId().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new EscalationStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        ChatEscalation escalation = ChatEscalation.register(
                command.conversationId(),
                command.statusId(),
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
