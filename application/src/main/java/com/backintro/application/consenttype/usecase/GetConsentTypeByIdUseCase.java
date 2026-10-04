package com.backintro.application.consenttype.usecase;

import com.backintro.application.consenttype.dto.ConsentTypeResponse;
import com.backintro.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;
import com.backintro.domain.consenttype.port.repository.ConsentTypeRepository;

public class GetConsentTypeByIdUseCase {

    private final ConsentTypeRepository consentTypeRepository;

    public GetConsentTypeByIdUseCase(
            ConsentTypeRepository consentTypeRepository
    ) {
        this.consentTypeRepository = consentTypeRepository;
    }

    public ConsentTypeResponse execute(ConsentTypeId id) {
        var consentType = consentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new ConsentTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new ConsentTypeResponse(
                consentType.id().value(),
                consentType.name(),
                consentType.code(),
                consentType.description()
        );
    }
}
