package com.backintro.application.professionalstudy.usecase;

import java.time.LocalDateTime;

import com.backintro.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import com.backintro.domain.professionalstudy.event.ProfessionalStudyDeletedEvent;
import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.backintro.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class DeleteProfessionalStudyUseCase {

    private final ProfessionalStudyRepository professionalStudyRepository;

    public DeleteProfessionalStudyUseCase(
            ProfessionalStudyRepository professionalStudyRepository
    ) {
        this.professionalStudyRepository = professionalStudyRepository;
    }

    public ProfessionalStudyDeletedEvent execute(ProfessionalStudyId id) {
        var professionalStudy = professionalStudyRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessionalStudyNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        professionalStudyRepository.delete(professionalStudy);

        return new ProfessionalStudyDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
