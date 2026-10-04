package com.backintro.application.encounterstatus.usecase;

import com.backintro.application.encounterstatus.command.RegisterEncounterStatusCommand;
import com.backintro.application.encounterstatus.dto.EncounterStatusResponse;
import com.backintro.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class RegisterEncounterStatusUseCase {

    private final EncounterStatusRepository encounterStatusRepository;

    public RegisterEncounterStatusUseCase(
            EncounterStatusRepository encounterStatusRepository
    ) {
        this.encounterStatusRepository = encounterStatusRepository;
    }

    public EncounterStatusResponse execute(
            RegisterEncounterStatusCommand command
    ) {
        EncounterStatus encounterStatus = EncounterStatus.register(
                command.name(),
                command.code()
        );

        EncounterStatus saved =
                encounterStatusRepository.save(encounterStatus);

        return new EncounterStatusResponse(
                saved.id().value(),
                saved.name(),
                saved.code()
        );
    }
}
