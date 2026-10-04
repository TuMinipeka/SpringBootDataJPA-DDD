package com.backintro.application.chatparticipant.usecase;

import java.util.List;

import com.backintro.application.chatparticipant.dto.ChatParticipantResponse;
import com.backintro.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.backintro.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class ListChatParticipantUseCase {

    private final ChatParticipantRepository chatParticipantRepository;

    public ListChatParticipantUseCase(
            ChatParticipantRepository chatParticipantRepository
    ) {
        this.chatParticipantRepository = chatParticipantRepository;
    }

    public List<ChatParticipantResponse> execute() {
        return chatParticipantRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
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
