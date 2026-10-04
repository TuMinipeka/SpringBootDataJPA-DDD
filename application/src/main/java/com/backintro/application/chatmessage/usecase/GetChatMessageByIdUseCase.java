package com.backintro.application.chatmessage.usecase;

import com.backintro.application.chatmessage.dto.ChatMessageResponse;
import com.backintro.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.backintro.domain.chatmessage.model.aggregate.ChatMessage;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;

public class GetChatMessageByIdUseCase {

    private final ChatMessageRepository chatMessageRepository;

    public GetChatMessageByIdUseCase(
            ChatMessageRepository chatMessageRepository
    ) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessageResponse execute(ChatMessageId id) {
        var chatMessage = chatMessageRepository.findById(id)
                .orElseThrow(() ->
                        new ChatMessageNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(chatMessage);
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
