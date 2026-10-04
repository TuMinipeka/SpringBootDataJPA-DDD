package com.backintro.application.encountertype.usecase;

import com.backintro.application.encountertype.command.RegisterEncounterTypeCommand;
import com.backintro.application.encountertype.dto.EncounterTypeResponse;
import com.backintro.domain.encountertype.model.aggregate.EncounterType;
import com.backintro.domain.encountertype.port.repository.EncounterTypeRepository;

public class RegisterEncounterTypeUseCase {

    private final EncounterTypeRepository encounterTypeRepository;

    public RegisterEncounterTypeUseCase(
            EncounterTypeRepository encounterTypeRepository
    ) {
        this.encounterTypeRepository = encounterTypeRepository;
    }

    public EncounterTypeResponse execute(
            RegisterEncounterTypeCommand command
    ) {
        EncounterType encounterType = EncounterType.register(
                command.name(),
                command.code()
        );

        EncounterType saved = encounterTypeRepository.save(encounterType);

        return new EncounterTypeResponse(
                saved.id().value(),
                saved.name(),
                saved.code()
        );
    }
}
