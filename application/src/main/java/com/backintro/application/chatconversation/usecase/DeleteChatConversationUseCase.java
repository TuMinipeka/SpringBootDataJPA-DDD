package com.backintro.application.chatconversation.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.backintro.domain.chatconversation.event.ChatConversationDeletedEvent;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;

public class DeleteChatConversationUseCase {

    private final ChatConversationRepository chatConversationRepository;

    public DeleteChatConversationUseCase(ChatConversationRepository chatConversationRepository) {
        this.chatConversationRepository = chatConversationRepository;
    }

    public ChatConversationDeletedEvent execute(ChatConversationId id) {
        var chatConversation = chatConversationRepository.findById(id)
                .orElseThrow(() ->
                        new ChatConversationNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        chatConversationRepository.delete(chatConversation);

        return new ChatConversationDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
