package com.backintro.application.chatescalationstatushistory.usecase;

import com.backintro.application.chatescalationstatushistory.command.UpdateChatEscalationStatusHistoryCommand;
import com.backintro.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.backintro.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import com.backintro.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.backintro.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.backintro.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class UpdateChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository historyRepository;
    private final EscalationStatusRepository statusRepository;

    public UpdateChatEscalationStatusHistoryUseCase(
            ChatEscalationStatusHistoryRepository historyRepository,
            EscalationStatusRepository statusRepository
    ) {
        this.historyRepository = historyRepository;
        this.statusRepository = statusRepository;
    }

    public ChatEscalationStatusHistoryResponse execute(
            UpdateChatEscalationStatusHistoryCommand command
    ) {
        var history = historyRepository.findById(command.id())
                .orElseThrow(() ->
                        new ChatEscalationStatusHistoryNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        validateStatus(command.escalationStatusId());

        history.update(command.escalationStatusId());

        return toResponse(historyRepository.save(history));
    }

    private void validateStatus(EscalationStatusId statusId) {
        statusRepository.findById(statusId)
                .orElseThrow(() ->
                        new EscalationStatusNotFoundApplicationException(
                                statusId.value().toString()
                        )
                );
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
