package com.backintro.application.chatconversation.usecase;

import com.backintro.application.chatconversation.dto.ChatConversationResponse;
import com.backintro.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.backintro.domain.chatconversation.model.aggregate.ChatConversation;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;

public class GetChatConversationByIdUseCase {

    private final ChatConversationRepository chatConversationRepository;

    public GetChatConversationByIdUseCase(
            ChatConversationRepository chatConversationRepository
    ) {
        this.chatConversationRepository = chatConversationRepository;
    }

    public ChatConversationResponse execute(ChatConversationId id) {
        var chatConversation = chatConversationRepository.findById(id)
                .orElseThrow(() ->
                        new ChatConversationNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(chatConversation);
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
