package com.backintro.application.encounterstatus.usecase;

import java.util.List;

import com.backintro.application.encounterstatus.dto.EncounterStatusResponse;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class ListEncounterStatusUseCase {

    private final EncounterStatusRepository encounterStatusRepository;

    public ListEncounterStatusUseCase(
            EncounterStatusRepository encounterStatusRepository
    ) {
        this.encounterStatusRepository = encounterStatusRepository;
    }

    public List<EncounterStatusResponse> execute() {
        return encounterStatusRepository.findAll()
                .stream()
                .map(encounterStatus ->
                        new EncounterStatusResponse(
                                encounterStatus.id().value(),
                                encounterStatus.name(),
                                encounterStatus.code()
                        )
                )
                .toList();
    }
}
