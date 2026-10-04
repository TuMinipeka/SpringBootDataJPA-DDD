package com.backintro.application.professionaltype.usecase;

import java.time.LocalDateTime;

import com.backintro.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import com.backintro.domain.professionaltype.event.ProfessionalTypeDeletedEvent;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.backintro.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class DeleteProfessionalTypeUseCase {

    private final ProfessionalTypeRepository professionalTypeRepository;

    public DeleteProfessionalTypeUseCase(
            ProfessionalTypeRepository professionalTypeRepository
    ) {
        this.professionalTypeRepository = professionalTypeRepository;
    }

    public ProfessionalTypeDeletedEvent execute(ProfessionalTypeId id) {
        var professionalType = professionalTypeRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessionalTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        professionalTypeRepository.delete(professionalType);

        return new ProfessionalTypeDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
