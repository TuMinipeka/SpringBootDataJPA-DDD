package com.backintro.application.consenttype.usecase;

import com.backintro.application.consenttype.command.RegisterConsentTypeCommand;
import com.backintro.application.consenttype.dto.ConsentTypeResponse;
import com.backintro.domain.consenttype.model.aggregate.ConsentType;
import com.backintro.domain.consenttype.port.repository.ConsentTypeRepository;

public class RegisterConsentTypeUseCase {

    private final ConsentTypeRepository consentTypeRepository;

    public RegisterConsentTypeUseCase(
            ConsentTypeRepository consentTypeRepository
    ) {
        this.consentTypeRepository = consentTypeRepository;
    }

    public ConsentTypeResponse execute(
            RegisterConsentTypeCommand command
    ) {
        ConsentType consentType = ConsentType.register(
                command.name(),
                command.code(),
                command.description()
        );

        ConsentType saved =
                consentTypeRepository.save(consentType);

        return new ConsentTypeResponse(
                saved.id().value(),
                saved.name(),
                saved.code(),
                saved.description()
        );
    }
}
