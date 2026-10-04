package com.backintro.application.clinicalnote.usecase;

import com.backintro.application.clinicalnote.command.RegisterClinicalNoteCommand;
import com.backintro.application.clinicalnote.dto.ClinicalNoteResponse;
import com.backintro.application.encounter.exception.EncounterNotFoundApplicationException;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.backintro.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class RegisterClinicalNoteUseCase {

    private final ClinicalNoteRepository clinicalNoteRepository;
    private final EncounterRepository encounterRepository;
    private final ProfessionalRepository professionalRepository;

    public RegisterClinicalNoteUseCase(
            ClinicalNoteRepository clinicalNoteRepository,
            EncounterRepository encounterRepository,
            ProfessionalRepository professionalRepository
    ) {
        this.clinicalNoteRepository = clinicalNoteRepository;
        this.encounterRepository = encounterRepository;
        this.professionalRepository = professionalRepository;
    }

    public ClinicalNoteResponse execute(
            RegisterClinicalNoteCommand command
    ) {
        encounterRepository.findById(command.encounterId())
                .orElseThrow(() ->
                        new EncounterNotFoundApplicationException(
                                command.encounterId().value().toString()
                        )
                );
        professionalRepository.findById(command.professionalId())
                .orElseThrow(() ->
                        new ProfessionalNotFoundApplicationException(
                                command.professionalId().value().toString()
                        )
                );

        ClinicalNote clinicalNote = ClinicalNote.register(
                command.encounterId(),
                command.professionalId(),
                command.subjective(),
                command.objective(),
                command.assessment(),
                command.plan(),
                command.additionalNotes()
        );

        return toResponse(clinicalNoteRepository.save(clinicalNote));
    }

    private ClinicalNoteResponse toResponse(ClinicalNote clinicalNote) {
        return new ClinicalNoteResponse(
                clinicalNote.id().value(),
                clinicalNote.subjective(),
                clinicalNote.objective(),
                clinicalNote.assessment(),
                clinicalNote.plan(),
                clinicalNote.additionalNotes(),
                clinicalNote.signedAt()
        );
    }
}
