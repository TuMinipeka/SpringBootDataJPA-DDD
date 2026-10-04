package com.backintro.application.chatescalationstatushistory.usecase;

import com.backintro.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.backintro.application.chatescalationstatushistory.command.RegisterChatEscalationStatusHistoryCommand;
import com.backintro.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.backintro.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.backintro.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.backintro.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class RegisterChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository historyRepository;
    private final ChatEscalationRepository escalationRepository;
    private final EscalationStatusRepository statusRepository;

    public RegisterChatEscalationStatusHistoryUseCase(
            ChatEscalationStatusHistoryRepository historyRepository,
            ChatEscalationRepository escalationRepository,
            EscalationStatusRepository statusRepository
    ) {
        this.historyRepository = historyRepository;
        this.escalationRepository = escalationRepository;
        this.statusRepository = statusRepository;
    }

    public ChatEscalationStatusHistoryResponse execute(
            RegisterChatEscalationStatusHistoryCommand command
    ) {
        escalationRepository.findById(command.escalationId())
                .orElseThrow(() ->
                        new ChatEscalationNotFoundApplicationException(
                                command.escalationId().value().toString()
                        )
                );
        validateStatus(command.escalationStatusId());

        ChatEscalationStatusHistory history =
                ChatEscalationStatusHistory.register(
                        command.escalationId(),
                        command.escalationStatusId()
                );

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
