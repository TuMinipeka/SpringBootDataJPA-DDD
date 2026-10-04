package com.backintro.application.professionalstudy.usecase;

import java.util.List;

import com.backintro.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.backintro.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class ListProfessionalStudyUseCase {

    private final ProfessionalStudyRepository professionalStudyRepository;

    public ListProfessionalStudyUseCase(
            ProfessionalStudyRepository professionalStudyRepository
    ) {
        this.professionalStudyRepository = professionalStudyRepository;
    }

    public List<ProfessionalStudyResponse> execute() {
        return professionalStudyRepository.findAll()
                .stream()
                .map(professionalStudy ->
                        new ProfessionalStudyResponse(
                                professionalStudy.id().value(),
                                professionalStudy.title(),
                                professionalStudy.university(),
                                professionalStudy.valid(),
                                professionalStudy.resolutionNumber()
                        )
                )
                .toList();
    }
}
