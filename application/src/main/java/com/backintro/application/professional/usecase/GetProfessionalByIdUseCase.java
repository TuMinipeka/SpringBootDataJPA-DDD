package com.backintro.application.professional.usecase;

import com.backintro.application.professional.dto.ProfessionalResponse;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class GetProfessionalByIdUseCase {

    private final ProfessionalRepository professionalRepository;

    public GetProfessionalByIdUseCase(
            ProfessionalRepository professionalRepository
    ) {
        this.professionalRepository = professionalRepository;
    }

    public ProfessionalResponse execute(ProfessionalId id) {
        var professional = professionalRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessionalNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new ProfessionalResponse(
                professional.id().value(),
                professional.documentNumber(),
                professional.firstName(),
                professional.lastName(),
                professional.licenseNumber()
        );
    }
}
