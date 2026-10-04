package com.backintro.application.professionalstudy.usecase;

import com.backintro.application.professionalstudy.command.UpdateProfessionalStudyCommand;
import com.backintro.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.backintro.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import com.backintro.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class UpdateProfessionalStudyUseCase {

    private final ProfessionalStudyRepository professionalStudyRepository;

    public UpdateProfessionalStudyUseCase(
            ProfessionalStudyRepository professionalStudyRepository
    ) {
        this.professionalStudyRepository = professionalStudyRepository;
    }

    public ProfessionalStudyResponse execute(
            UpdateProfessionalStudyCommand command
    ) {
        var professionalStudy = professionalStudyRepository.findById(
                        command.id()
                )
                .orElseThrow(() ->
                        new ProfessionalStudyNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        professionalStudy.update(
                command.title(),
                command.university(),
                command.valid(),
                command.resolutionNumber()
        );

        var updated = professionalStudyRepository.save(professionalStudy);

        return new ProfessionalStudyResponse(
                updated.id().value(),
                updated.title(),
                updated.university(),
                updated.valid(),
                updated.resolutionNumber()
        );
    }
}
