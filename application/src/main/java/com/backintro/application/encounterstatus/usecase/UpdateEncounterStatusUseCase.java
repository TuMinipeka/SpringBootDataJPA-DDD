package com.backintro.application.encounterstatus.usecase;

import com.backintro.application.encounterstatus.command.UpdateEncounterStatusCommand;
import com.backintro.application.encounterstatus.dto.EncounterStatusResponse;
import com.backintro.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class UpdateEncounterStatusUseCase {

    private final EncounterStatusRepository encounterStatusRepository;

    public UpdateEncounterStatusUseCase(
            EncounterStatusRepository encounterStatusRepository
    ) {
        this.encounterStatusRepository = encounterStatusRepository;
    }

    public EncounterStatusResponse execute(
            UpdateEncounterStatusCommand command
    ) {
        var encounterStatus = encounterStatusRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new EncounterStatusNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        encounterStatus.update(command.name(), command.code());

        var updated = encounterStatusRepository.save(encounterStatus);

        return new EncounterStatusResponse(
                updated.id().value(),
                updated.name(),
                updated.code()
        );
    }
}
