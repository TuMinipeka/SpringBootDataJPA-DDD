package com.backintro.application.encountermodality.usecase;

import com.backintro.application.encountermodality.command.UpdateEncounterModalityCommand;
import com.backintro.application.encountermodality.dto.EncounterModalityResponse;
import com.backintro.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.backintro.domain.encountermodality.port.repository.EncounterModalityRepository;

public class UpdateEncounterModalityUseCase {

    private final EncounterModalityRepository encounterModalityRepository;

    public UpdateEncounterModalityUseCase(
            EncounterModalityRepository encounterModalityRepository
    ) {
        this.encounterModalityRepository = encounterModalityRepository;
    }

    public EncounterModalityResponse execute(
            UpdateEncounterModalityCommand command
    ) {
        var encounterModality = encounterModalityRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new EncounterModalityNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        encounterModality.update(command.name(), command.code());

        var updated = encounterModalityRepository.save(encounterModality);

        return new EncounterModalityResponse(
                updated.id().value(),
                updated.name(),
                updated.code()
        );
    }
}
