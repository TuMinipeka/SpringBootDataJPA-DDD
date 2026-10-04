package com.backintro.application.chatconversation.usecase;

import com.backintro.application.chatconversation.command.RegisterChatConversationCommand;
import com.backintro.application.chatconversation.dto.ChatConversationResponse;
import com.backintro.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.backintro.application.priority.exception.PriorityNotFoundApplicationException;
import com.backintro.domain.chatconversation.model.aggregate.ChatConversation;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;
import com.backintro.domain.priority.port.repository.PriorityRepository;

public class RegisterChatConversationUseCase {

    private final ChatConversationRepository chatConversationRepository;
    private final ConversationStatusRepository conversationStatusRepository;
    private final PriorityRepository priorityRepository;

    public RegisterChatConversationUseCase(
            ChatConversationRepository chatConversationRepository,
            ConversationStatusRepository conversationStatusRepository,
            PriorityRepository priorityRepository
    ) {
        this.chatConversationRepository = chatConversationRepository;
        this.conversationStatusRepository = conversationStatusRepository;
        this.priorityRepository = priorityRepository;
    }

    public ChatConversationResponse execute(
            RegisterChatConversationCommand command
    ) {
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

        ChatConversation chatConversation = ChatConversation.register(
                command.conversationStatusId(),
                command.priorityId(),
                command.lastMessageAt()
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
