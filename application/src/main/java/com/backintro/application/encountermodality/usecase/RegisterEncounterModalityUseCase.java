package com.backintro.application.encountermodality.usecase;

import com.backintro.application.encountermodality.command.RegisterEncounterModalityCommand;
import com.backintro.application.encountermodality.dto.EncounterModalityResponse;
import com.backintro.domain.encountermodality.model.aggregate.EncounterModality;
import com.backintro.domain.encountermodality.port.repository.EncounterModalityRepository;

public class RegisterEncounterModalityUseCase {

    private final EncounterModalityRepository encounterModalityRepository;

    public RegisterEncounterModalityUseCase(
            EncounterModalityRepository encounterModalityRepository
    ) {
        this.encounterModalityRepository = encounterModalityRepository;
    }

    public EncounterModalityResponse execute(
            RegisterEncounterModalityCommand command
    ) {
        EncounterModality encounterModality = EncounterModality.register(
                command.name(),
                command.code()
        );

        EncounterModality saved =
                encounterModalityRepository.save(encounterModality);

        return new EncounterModalityResponse(
                saved.id().value(),
                saved.name(),
                saved.code()
        );
    }
}
