package com.backintro.application.chatescalationstatushistory.usecase;

import java.util.List;

import com.backintro.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.backintro.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.backintro.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class ListChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository historyRepository;

    public ListChatEscalationStatusHistoryUseCase(
            ChatEscalationStatusHistoryRepository historyRepository
    ) {
        this.historyRepository = historyRepository;
    }

    public List<ChatEscalationStatusHistoryResponse> execute() {
        return historyRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
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
