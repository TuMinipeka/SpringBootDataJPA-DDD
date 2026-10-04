package com.backintro.application.encounter.usecase;

import com.backintro.application.encounter.command.UpdateEncounterCommand;
import com.backintro.application.encounter.dto.EncounterResponse;
import com.backintro.application.encounter.exception.EncounterNotFoundApplicationException;
import com.backintro.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class UpdateEncounterUseCase {

    private final EncounterRepository encounterRepository;
    private final EncounterStatusRepository statusRepository;

    public UpdateEncounterUseCase(
            EncounterRepository encounterRepository,
            EncounterStatusRepository statusRepository
    ) {
        this.encounterRepository = encounterRepository;
        this.statusRepository = statusRepository;
    }

    public EncounterResponse execute(UpdateEncounterCommand command) {
        var encounter = encounterRepository.findById(command.id())
                .orElseThrow(() ->
                        new EncounterNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new EncounterStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        encounter.update(
                command.endedAt(),
                command.reasonForVisit(),
                command.currentCondition(),
                command.statusId()
        );

        var updated = encounterRepository.save(encounter);

        return new EncounterResponse(
                updated.id().value(),
                updated.startedAt(),
                updated.endedAt(),
                updated.reasonForVisit(),
                updated.currentCondition()
        );
    }
}
