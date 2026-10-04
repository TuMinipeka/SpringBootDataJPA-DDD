package com.backintro.application.chatconversation.usecase;

import java.util.List;

import com.backintro.application.chatconversation.dto.ChatConversationResponse;
import com.backintro.domain.chatconversation.model.aggregate.ChatConversation;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;

public class ListChatConversationUseCase {

    private final ChatConversationRepository chatConversationRepository;

    public ListChatConversationUseCase(
            ChatConversationRepository chatConversationRepository
    ) {
        this.chatConversationRepository = chatConversationRepository;
    }

    public List<ChatConversationResponse> execute() {
        return chatConversationRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
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
