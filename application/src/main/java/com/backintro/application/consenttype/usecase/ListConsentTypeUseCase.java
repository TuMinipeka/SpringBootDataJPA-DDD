package com.backintro.application.consenttype.usecase;

import java.util.List;

import com.backintro.application.consenttype.dto.ConsentTypeResponse;
import com.backintro.domain.consenttype.port.repository.ConsentTypeRepository;

public class ListConsentTypeUseCase {

    private final ConsentTypeRepository consentTypeRepository;

    public ListConsentTypeUseCase(
            ConsentTypeRepository consentTypeRepository
    ) {
        this.consentTypeRepository = consentTypeRepository;
    }

    public List<ConsentTypeResponse> execute() {
        return consentTypeRepository.findAll()
                .stream()
                .map(consentType ->
                        new ConsentTypeResponse(
                                consentType.id().value(),
                                consentType.name(),
                                consentType.code(),
                                consentType.description()
                        )
                )
                .toList();
    }
}
