package com.backintro.application.clinicalnote.usecase;

import com.backintro.application.clinicalnote.dto.ClinicalNoteResponse;
import com.backintro.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import com.backintro.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.backintro.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class GetClinicalNoteByIdUseCase {

    private final ClinicalNoteRepository clinicalNoteRepository;

    public GetClinicalNoteByIdUseCase(
            ClinicalNoteRepository clinicalNoteRepository
    ) {
        this.clinicalNoteRepository = clinicalNoteRepository;
    }

    public ClinicalNoteResponse execute(ClinicalNoteId id) {
        var clinicalNote = clinicalNoteRepository.findById(id)
                .orElseThrow(() ->
                        new ClinicalNoteNotFoundApplicationException(
                                id.value().toString()
                        )
                );

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
