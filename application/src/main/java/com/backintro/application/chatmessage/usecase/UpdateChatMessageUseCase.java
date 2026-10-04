package com.backintro.application.chatmessage.usecase;

import com.backintro.application.chatmessage.command.UpdateChatMessageCommand;
import com.backintro.application.chatmessage.dto.ChatMessageResponse;
import com.backintro.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.backintro.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.backintro.domain.chatmessage.model.aggregate.ChatMessage;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;

public class UpdateChatMessageUseCase {

    private final ChatMessageRepository chatMessageRepository;
    private final MessageTypeRepository messageTypeRepository;

    public UpdateChatMessageUseCase(
            ChatMessageRepository chatMessageRepository,
            MessageTypeRepository messageTypeRepository
    ) {
        this.chatMessageRepository = chatMessageRepository;
        this.messageTypeRepository = messageTypeRepository;
    }

    public ChatMessageResponse execute(UpdateChatMessageCommand command) {
        var chatMessage = chatMessageRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new ChatMessageNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        messageTypeRepository.findById(command.messageTypeId())
                .orElseThrow(() ->
                        new MessageTypeNotFoundApplicationException(
                                command.messageTypeId().value().toString()
                        )
                );

        chatMessage.update(
                command.messageTypeId(),
                command.content(),
                command.metadata()
        );

        return toResponse(chatMessageRepository.save(chatMessage));
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
