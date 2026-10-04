package com.backintro.application.escalationstatus.usecase;

import com.backintro.application.escalationstatus.dto.EscalationStatusResponse;
import com.backintro.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class GetEscalationStatusByIdUseCase {

    private final EscalationStatusRepository escalationStatusRepository;

    public GetEscalationStatusByIdUseCase(
            EscalationStatusRepository escalationStatusRepository
    ) {
        this.escalationStatusRepository = escalationStatusRepository;
    }

    public EscalationStatusResponse execute(EscalationStatusId id) {
        var escalationStatus = escalationStatusRepository.findById(id)
                .orElseThrow(() ->
                        new EscalationStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new EscalationStatusResponse(
                escalationStatus.id().value(),
                escalationStatus.nameStatus()
        );
    }
}
