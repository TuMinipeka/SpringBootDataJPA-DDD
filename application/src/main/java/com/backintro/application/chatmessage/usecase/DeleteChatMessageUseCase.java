package com.backintro.application.chatmessage.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.backintro.domain.chatmessage.event.ChatMessageDeletedEvent;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;

public class DeleteChatMessageUseCase {

    private final ChatMessageRepository chatMessageRepository;

    public DeleteChatMessageUseCase(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessageDeletedEvent execute(ChatMessageId id) {
        var chatMessage = chatMessageRepository.findById(id)
                .orElseThrow(() ->
                        new ChatMessageNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        chatMessageRepository.delete(chatMessage);

        return new ChatMessageDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
