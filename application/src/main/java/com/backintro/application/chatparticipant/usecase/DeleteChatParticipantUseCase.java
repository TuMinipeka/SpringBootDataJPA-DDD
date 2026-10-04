package com.backintro.application.chatparticipant.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import com.backintro.domain.chatparticipant.event.ChatParticipantDeletedEvent;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class DeleteChatParticipantUseCase {

    private final ChatParticipantRepository chatParticipantRepository;

    public DeleteChatParticipantUseCase(ChatParticipantRepository chatParticipantRepository) {
        this.chatParticipantRepository = chatParticipantRepository;
    }

    public ChatParticipantDeletedEvent execute(ChatParticipantId id) {
        var chatParticipant = chatParticipantRepository.findById(id)
                .orElseThrow(() ->
                        new ChatParticipantNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        chatParticipantRepository.delete(chatParticipant);

        return new ChatParticipantDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
