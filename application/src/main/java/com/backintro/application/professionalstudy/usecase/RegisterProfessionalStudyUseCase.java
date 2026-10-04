package com.backintro.application.professionalstudy.usecase;

import com.backintro.application.country.exception.CountryNotFoundApplicationException;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.application.professionalstudy.command.RegisterProfessionalStudyCommand;
import com.backintro.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.backintro.application.study.exception.StudyNotFoundApplicationException;
import com.backintro.domain.country.port.repository.CountryRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.backintro.domain.professionalstudy.port.repository.ProfessionalStudyRepository;
import com.backintro.domain.study.port.repository.StudyRepository;

public class RegisterProfessionalStudyUseCase {

    private final ProfessionalStudyRepository professionalStudyRepository;
    private final StudyRepository studyRepository;
    private final ProfessionalRepository professionalRepository;
    private final CountryRepository countryRepository;

    public RegisterProfessionalStudyUseCase(
            ProfessionalStudyRepository professionalStudyRepository,
            StudyRepository studyRepository,
            ProfessionalRepository professionalRepository,
            CountryRepository countryRepository
    ) {
        this.professionalStudyRepository = professionalStudyRepository;
        this.studyRepository = studyRepository;
        this.professionalRepository = professionalRepository;
        this.countryRepository = countryRepository;
    }

    public ProfessionalStudyResponse execute(
            RegisterProfessionalStudyCommand command
    ) {
        studyRepository.findById(command.studyId())
                .orElseThrow(() ->
                        new StudyNotFoundApplicationException(
                                command.studyId().value().toString()
                        )
                );
        professionalRepository.findById(command.professionalId())
                .orElseThrow(() ->
                        new ProfessionalNotFoundApplicationException(
                                command.professionalId().value().toString()
                        )
                );
        if (command.countryId() != null) {
            countryRepository.findById(command.countryId())
                    .orElseThrow(() ->
                            new CountryNotFoundApplicationException(
                                    command.countryId().value().toString()
                            )
                    );
        }

        ProfessionalStudy professionalStudy = ProfessionalStudy.register(
                command.studyId(),
                command.professionalId(),
                command.title(),
                command.university(),
                command.resolutionNumber(),
                command.countryId()
        );

        ProfessionalStudy saved =
                professionalStudyRepository.save(professionalStudy);

        return toResponse(saved);
    }

    private ProfessionalStudyResponse toResponse(
            ProfessionalStudy professionalStudy
    ) {
        return new ProfessionalStudyResponse(
                professionalStudy.id().value(),
                professionalStudy.title(),
                professionalStudy.university(),
                professionalStudy.valid(),
                professionalStudy.resolutionNumber()
        );
    }
}
