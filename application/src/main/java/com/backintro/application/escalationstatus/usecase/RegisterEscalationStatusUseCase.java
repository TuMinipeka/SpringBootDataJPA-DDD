package com.backintro.application.escalationstatus.usecase;

import com.backintro.application.escalationstatus.command.RegisterEscalationStatusCommand;
import com.backintro.application.escalationstatus.dto.EscalationStatusResponse;
import com.backintro.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class RegisterEscalationStatusUseCase {

    private final EscalationStatusRepository escalationStatusRepository;

    public RegisterEscalationStatusUseCase(
            EscalationStatusRepository escalationStatusRepository
    ) {
        this.escalationStatusRepository = escalationStatusRepository;
    }

    public EscalationStatusResponse execute(
            RegisterEscalationStatusCommand command
    ) {
        EscalationStatus escalationStatus =
                EscalationStatus.register(command.nameStatus());
        EscalationStatus saved =
                escalationStatusRepository.save(escalationStatus);

        return new EscalationStatusResponse(
                saved.id().value(),
                saved.nameStatus()
        );
    }
}
