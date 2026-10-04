package com.backintro.application.professionalstudy.usecase;

import com.backintro.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.backintro.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.backintro.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class GetProfessionalStudyByIdUseCase {

    private final ProfessionalStudyRepository professionalStudyRepository;

    public GetProfessionalStudyByIdUseCase(
            ProfessionalStudyRepository professionalStudyRepository
    ) {
        this.professionalStudyRepository = professionalStudyRepository;
    }

    public ProfessionalStudyResponse execute(ProfessionalStudyId id) {
        var professionalStudy = professionalStudyRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessionalStudyNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new ProfessionalStudyResponse(
                professionalStudy.id().value(),
                professionalStudy.title(),
                professionalStudy.university(),
                professionalStudy.valid(),
                professionalStudy.resolutionNumber()
        );
    }
}
