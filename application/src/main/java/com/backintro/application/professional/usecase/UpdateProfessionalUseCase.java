package com.backintro.application.professional.usecase;

import com.backintro.application.professional.command.UpdateProfessionalCommand;
import com.backintro.application.professional.dto.ProfessionalResponse;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class UpdateProfessionalUseCase {

    private final ProfessionalRepository professionalRepository;

    public UpdateProfessionalUseCase(
            ProfessionalRepository professionalRepository
    ) {
        this.professionalRepository = professionalRepository;
    }

    public ProfessionalResponse execute(UpdateProfessionalCommand command) {
        var professional = professionalRepository.findById(command.id())
                .orElseThrow(() ->
                        new ProfessionalNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        professional.update(
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.licenseNumber()
        );

        var updated = professionalRepository.save(professional);

        return new ProfessionalResponse(
                updated.id().value(),
                updated.documentNumber(),
                updated.firstName(),
                updated.lastName(),
                updated.licenseNumber()
        );
    }
}
