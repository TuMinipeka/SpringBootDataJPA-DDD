package com.backintro.application.encountertype.usecase;

import com.backintro.application.encountertype.command.UpdateEncounterTypeCommand;
import com.backintro.application.encountertype.dto.EncounterTypeResponse;
import com.backintro.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import com.backintro.domain.encountertype.port.repository.EncounterTypeRepository;

public class UpdateEncounterTypeUseCase {

    private final EncounterTypeRepository encounterTypeRepository;

    public UpdateEncounterTypeUseCase(
            EncounterTypeRepository encounterTypeRepository
    ) {
        this.encounterTypeRepository = encounterTypeRepository;
    }

    public EncounterTypeResponse execute(UpdateEncounterTypeCommand command) {
        var encounterType = encounterTypeRepository.findById(command.id())
                .orElseThrow(() ->
                        new EncounterTypeNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        encounterType.update(command.name(), command.code());

        var updated = encounterTypeRepository.save(encounterType);

        return new EncounterTypeResponse(
                updated.id().value(),
                updated.name(),
                updated.code()
        );
    }
}
