package com.backintro.application.escalationstatus.usecase;

import java.time.LocalDateTime;

import com.backintro.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.backintro.domain.escalationstatus.event.EscalationStatusDeletedEvent;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class DeleteEscalationStatusUseCase {

    private final EscalationStatusRepository escalationStatusRepository;

    public DeleteEscalationStatusUseCase(
            EscalationStatusRepository escalationStatusRepository
    ) {
        this.escalationStatusRepository = escalationStatusRepository;
    }

    public EscalationStatusDeletedEvent execute(EscalationStatusId id) {
        var escalationStatus = escalationStatusRepository.findById(id)
                .orElseThrow(() ->
                        new EscalationStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        escalationStatusRepository.delete(escalationStatus);

        return new EscalationStatusDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
