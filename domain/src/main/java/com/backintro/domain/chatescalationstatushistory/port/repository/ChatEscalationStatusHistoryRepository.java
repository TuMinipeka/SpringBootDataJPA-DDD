package com.backintro.domain.chatescalationstatushistory.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public interface ChatEscalationStatusHistoryRepository {

    ChatEscalationStatusHistory save(ChatEscalationStatusHistory history);

    Optional<ChatEscalationStatusHistory> findById(
            ChatEscalationStatusHistoryId id
    );

    List<ChatEscalationStatusHistory> findAll();

    void delete(ChatEscalationStatusHistory history);
}
