package com.backintro.application.consenttype.usecase;

import com.backintro.application.consenttype.command.UpdateConsentTypeCommand;
import com.backintro.application.consenttype.dto.ConsentTypeResponse;
import com.backintro.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import com.backintro.domain.consenttype.port.repository.ConsentTypeRepository;

public class UpdateConsentTypeUseCase {

    private final ConsentTypeRepository consentTypeRepository;

    public UpdateConsentTypeUseCase(
            ConsentTypeRepository consentTypeRepository
    ) {
        this.consentTypeRepository = consentTypeRepository;
    }

    public ConsentTypeResponse execute(
            UpdateConsentTypeCommand command
    ) {
        var consentType = consentTypeRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new ConsentTypeNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        consentType.update(
                command.name(),
                command.code(),
                command.description()
        );

        var updated = consentTypeRepository.save(consentType);

        return new ConsentTypeResponse(
                updated.id().value(),
                updated.name(),
                updated.code(),
                updated.description()
        );
    }
}
