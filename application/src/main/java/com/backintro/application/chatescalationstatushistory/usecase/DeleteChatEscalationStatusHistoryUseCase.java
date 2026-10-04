package com.backintro.application.chatescalationstatushistory.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import com.backintro.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryDeletedEvent;
import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.backintro.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class DeleteChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository historyRepository;

    public DeleteChatEscalationStatusHistoryUseCase(
            ChatEscalationStatusHistoryRepository historyRepository
    ) {
        this.historyRepository = historyRepository;
    }

    public ChatEscalationStatusHistoryDeletedEvent execute(
            ChatEscalationStatusHistoryId id
    ) {
        var history = historyRepository.findById(id)
                .orElseThrow(() ->
                        new ChatEscalationStatusHistoryNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        historyRepository.delete(history);

        return new ChatEscalationStatusHistoryDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
