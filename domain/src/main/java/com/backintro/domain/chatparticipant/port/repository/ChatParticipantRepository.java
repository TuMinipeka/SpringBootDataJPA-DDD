package com.backintro.domain.chatparticipant.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;

public interface ChatParticipantRepository {

    ChatParticipant save(ChatParticipant chatParticipant);

    Optional<ChatParticipant> findById(ChatParticipantId id);

    List<ChatParticipant> findAll();

    void delete(ChatParticipant chatParticipant);
}
