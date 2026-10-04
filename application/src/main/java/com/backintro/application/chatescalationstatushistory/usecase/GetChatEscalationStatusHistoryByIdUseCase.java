package com.backintro.application.chatescalationstatushistory.usecase;

import com.backintro.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.backintro.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import com.backintro.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.backintro.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class GetChatEscalationStatusHistoryByIdUseCase {

    private final ChatEscalationStatusHistoryRepository historyRepository;

    public GetChatEscalationStatusHistoryByIdUseCase(
            ChatEscalationStatusHistoryRepository historyRepository
    ) {
        this.historyRepository = historyRepository;
    }

    public ChatEscalationStatusHistoryResponse execute(
            ChatEscalationStatusHistoryId id
    ) {
        var history = historyRepository.findById(id)
                .orElseThrow(() ->
                        new ChatEscalationStatusHistoryNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(history);
    }

    private ChatEscalationStatusHistoryResponse toResponse(
            ChatEscalationStatusHistory history
    ) {
        return new ChatEscalationStatusHistoryResponse(
                history.id().value(),
                history.escalationId().value(),
                history.escalationStatusId().value()
        );
    }
}
