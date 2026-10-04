package com.backintro.application.encounterstatus.usecase;

import com.backintro.application.encounterstatus.dto.EncounterStatusResponse;
import com.backintro.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class GetEncounterStatusByIdUseCase {

    private final EncounterStatusRepository encounterStatusRepository;

    public GetEncounterStatusByIdUseCase(
            EncounterStatusRepository encounterStatusRepository
    ) {
        this.encounterStatusRepository = encounterStatusRepository;
    }

    public EncounterStatusResponse execute(EncounterStatusId id) {
        var encounterStatus = encounterStatusRepository.findById(id)
                .orElseThrow(() ->
                        new EncounterStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new EncounterStatusResponse(
                encounterStatus.id().value(),
                encounterStatus.name(),
                encounterStatus.code()
        );
    }
}
