package com.backintro.domain.conversationstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;

public interface ConversationStatusRepository {

    ConversationStatus save(ConversationStatus conversationStatus);

    Optional<ConversationStatus> findById(ConversationStatusId id);

    List<ConversationStatus> findAll();

    boolean existsByNameStatus(String nameStatus);

    void delete(ConversationStatus conversationStatus);
}
