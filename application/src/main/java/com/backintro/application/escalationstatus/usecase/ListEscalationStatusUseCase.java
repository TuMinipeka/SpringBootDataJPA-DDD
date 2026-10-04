package com.backintro.application.escalationstatus.usecase;

import java.util.List;

import com.backintro.application.escalationstatus.dto.EscalationStatusResponse;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class ListEscalationStatusUseCase {

    private final EscalationStatusRepository escalationStatusRepository;

    public ListEscalationStatusUseCase(
            EscalationStatusRepository escalationStatusRepository
    ) {
        this.escalationStatusRepository = escalationStatusRepository;
    }

    public List<EscalationStatusResponse> execute() {
        return escalationStatusRepository.findAll()
                .stream()
                .map(escalationStatus ->
                        new EscalationStatusResponse(
                                escalationStatus.id().value(),
                                escalationStatus.nameStatus()
                        )
                )
                .toList();
    }
}
