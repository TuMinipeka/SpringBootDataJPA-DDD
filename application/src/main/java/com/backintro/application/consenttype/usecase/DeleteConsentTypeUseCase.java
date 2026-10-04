package com.backintro.application.consenttype.usecase;

import java.time.LocalDateTime;

import com.backintro.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import com.backintro.domain.consenttype.event.ConsentTypeDeletedEvent;
import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;
import com.backintro.domain.consenttype.port.repository.ConsentTypeRepository;

public class DeleteConsentTypeUseCase {

    private final ConsentTypeRepository consentTypeRepository;

    public DeleteConsentTypeUseCase(
            ConsentTypeRepository consentTypeRepository
    ) {
        this.consentTypeRepository = consentTypeRepository;
    }

    public ConsentTypeDeletedEvent execute(ConsentTypeId id) {
        var consentType = consentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new ConsentTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        consentTypeRepository.delete(consentType);

        return new ConsentTypeDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
