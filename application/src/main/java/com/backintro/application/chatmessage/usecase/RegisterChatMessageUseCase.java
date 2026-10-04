package com.backintro.application.chatmessage.usecase;

import com.backintro.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.backintro.application.chatmessage.command.RegisterChatMessageCommand;
import com.backintro.application.chatmessage.dto.ChatMessageResponse;
import com.backintro.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import com.backintro.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.chatmessage.model.aggregate.ChatMessage;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;
import com.backintro.domain.chatparticipant.port.repository.ChatParticipantRepository;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;

public class RegisterChatMessageUseCase {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatConversationRepository chatConversationRepository;
    private final MessageTypeRepository messageTypeRepository;
    private final ChatParticipantRepository chatParticipantRepository;

    public RegisterChatMessageUseCase(
            ChatMessageRepository chatMessageRepository,
            ChatConversationRepository chatConversationRepository,
            MessageTypeRepository messageTypeRepository,
            ChatParticipantRepository chatParticipantRepository
    ) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatConversationRepository = chatConversationRepository;
        this.messageTypeRepository = messageTypeRepository;
        this.chatParticipantRepository = chatParticipantRepository;
    }

    public ChatMessageResponse execute(RegisterChatMessageCommand command) {
        chatConversationRepository.findById(command.conversationId())
                .orElseThrow(() ->
                        new ChatConversationNotFoundApplicationException(
                                command.conversationId().value().toString()
                        )
                );
        messageTypeRepository.findById(command.messageTypeId())
                .orElseThrow(() ->
                        new MessageTypeNotFoundApplicationException(
                                command.messageTypeId().value().toString()
                        )
                );
        chatParticipantRepository.findById(command.participantId())
                .orElseThrow(() ->
                        new ChatParticipantNotFoundApplicationException(
                                command.participantId().value().toString()
                        )
                );

        ChatMessage chatMessage = ChatMessage.register(
                command.conversationId(),
                command.messageTypeId(),
                command.participantId(),
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
