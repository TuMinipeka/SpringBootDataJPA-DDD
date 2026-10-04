package com.backintro.application.chatmessage.usecase;

import java.util.List;

import com.backintro.application.chatmessage.dto.ChatMessageResponse;
import com.backintro.domain.chatmessage.model.aggregate.ChatMessage;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;

public class ListChatMessageUseCase {

    private final ChatMessageRepository chatMessageRepository;

    public ListChatMessageUseCase(
            ChatMessageRepository chatMessageRepository
    ) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public List<ChatMessageResponse> execute() {
        return chatMessageRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ChatMessageResponse toResponse(ChatMessage chatMessage) {
        return new ChatMessageResponse(
                chatMessage.id().value(),
                chatMessage.conversationId().value(),
                chatMessage.messageTypeId().value(),
                chatMessage.participantId().value(),
                chatMessage.content(),
                chatMessage.metadata()
        );
    }
}
