package com.backintro.application.chatparticipant.usecase;

import com.backintro.application.chatparticipant.dto.ChatParticipantResponse;
import com.backintro.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import com.backintro.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class GetChatParticipantByIdUseCase {

    private final ChatParticipantRepository chatParticipantRepository;

    public GetChatParticipantByIdUseCase(
            ChatParticipantRepository chatParticipantRepository
    ) {
        this.chatParticipantRepository = chatParticipantRepository;
    }

    public ChatParticipantResponse execute(ChatParticipantId id) {
        var chatParticipant = chatParticipantRepository.findById(id)
                .orElseThrow(() ->
                        new ChatParticipantNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(chatParticipant);
    }

    private ChatParticipantResponse toResponse(
            ChatParticipant chatParticipant
    ) {
        return new ChatParticipantResponse(
                chatParticipant.id().value(),
                chatParticipant.conversationId().value(),
                chatParticipant.participantTypeId().value(),
                chatParticipant.patientId() == null
                        ? null
                        : chatParticipant.patientId().value(),
                chatParticipant.professionalId() == null
                        ? null
                        : chatParticipant.professionalId().value()
        );
    }
}
