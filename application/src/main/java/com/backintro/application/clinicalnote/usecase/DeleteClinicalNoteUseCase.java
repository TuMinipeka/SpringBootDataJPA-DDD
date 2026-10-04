package com.backintro.application.clinicalnote.usecase;

import java.time.LocalDateTime;

import com.backintro.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import com.backintro.domain.clinicalnote.event.ClinicalNoteDeletedEvent;
import com.backintro.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.backintro.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class DeleteClinicalNoteUseCase {

    private final ClinicalNoteRepository clinicalNoteRepository;

    public DeleteClinicalNoteUseCase(
            ClinicalNoteRepository clinicalNoteRepository
    ) {
        this.clinicalNoteRepository = clinicalNoteRepository;
    }

    public ClinicalNoteDeletedEvent execute(ClinicalNoteId id) {
        var clinicalNote = clinicalNoteRepository.findById(id)
                .orElseThrow(() ->
                        new ClinicalNoteNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        clinicalNoteRepository.delete(clinicalNote);

        return new ClinicalNoteDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
