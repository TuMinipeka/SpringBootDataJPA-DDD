package com.backintro.application.chatconversation.usecase;

import com.backintro.application.chatconversation.command.UpdateChatConversationCommand;
import com.backintro.application.chatconversation.dto.ChatConversationResponse;
import com.backintro.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.backintro.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.backintro.application.priority.exception.PriorityNotFoundApplicationException;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.domain.chatconversation.model.aggregate.ChatConversation;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;
import com.backintro.domain.priority.port.repository.PriorityRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class UpdateChatConversationUseCase {

    private final ChatConversationRepository chatConversationRepository;
    private final ConversationStatusRepository conversationStatusRepository;
    private final PriorityRepository priorityRepository;
    private final ProfessionalRepository professionalRepository;

    public UpdateChatConversationUseCase(
            ChatConversationRepository chatConversationRepository,
            ConversationStatusRepository conversationStatusRepository,
            PriorityRepository priorityRepository,
            ProfessionalRepository professionalRepository
    ) {
        this.chatConversationRepository = chatConversationRepository;
        this.conversationStatusRepository = conversationStatusRepository;
        this.priorityRepository = priorityRepository;
        this.professionalRepository = professionalRepository;
    }

    public ChatConversationResponse execute(
            UpdateChatConversationCommand command
    ) {
        var chatConversation = chatConversationRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new ChatConversationNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        conversationStatusRepository
                .findById(command.conversationStatusId())
                .orElseThrow(() ->
                        new ConversationStatusNotFoundApplicationException(
                                command.conversationStatusId()
                                        .value()
                                        .toString()
                        )
                );
        if (command.priorityId() != null) {
            priorityRepository.findById(command.priorityId())
                    .orElseThrow(() ->
                            new PriorityNotFoundApplicationException(
                                    command.priorityId().value().toString()
                            )
                    );
        }
        if (command.closedBy() != null) {
            professionalRepository.findById(command.closedBy())
                    .orElseThrow(() ->
                            new ProfessionalNotFoundApplicationException(
                                    command.closedBy().value().toString()
                            )
                    );
        }

        chatConversation.update(
                command.conversationStatusId(),
                command.priorityId(),
                command.lastMessageAt(),
                command.closed(),
                command.closedAt(),
                command.closedBy()
        );

        return toResponse(
                chatConversationRepository.save(chatConversation)
        );
    }

    private ChatConversationResponse toResponse(
            ChatConversation chatConversation
    ) {
        return new ChatConversationResponse(
                chatConversation.id().value(),
                chatConversation.conversationStatusId().value(),
                chatConversation.priorityId() == null
                        ? null
                        : chatConversation.priorityId().value(),
                chatConversation.lastMessageAt(),
                chatConversation.closed(),
                chatConversation.closedAt(),
                chatConversation.closedBy() == null
                        ? null
                        : chatConversation.closedBy().value()
        );
    }
}
