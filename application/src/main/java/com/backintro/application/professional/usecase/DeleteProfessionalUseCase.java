package com.backintro.application.professional.usecase;

import java.time.LocalDateTime;

import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.domain.professional.event.ProfessionalDeletedEvent;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class DeleteProfessionalUseCase {

    private final ProfessionalRepository professionalRepository;

    public DeleteProfessionalUseCase(
            ProfessionalRepository professionalRepository
    ) {
        this.professionalRepository = professionalRepository;
    }

    public ProfessionalDeletedEvent execute(ProfessionalId id) {
        var professional = professionalRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessionalNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        professionalRepository.delete(professional);

        return new ProfessionalDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
