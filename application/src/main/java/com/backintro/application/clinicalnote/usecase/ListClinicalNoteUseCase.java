package com.backintro.application.clinicalnote.usecase;

import java.util.List;

import com.backintro.application.clinicalnote.dto.ClinicalNoteResponse;
import com.backintro.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class ListClinicalNoteUseCase {

    private final ClinicalNoteRepository clinicalNoteRepository;

    public ListClinicalNoteUseCase(
            ClinicalNoteRepository clinicalNoteRepository
    ) {
        this.clinicalNoteRepository = clinicalNoteRepository;
    }

    public List<ClinicalNoteResponse> execute() {
        return clinicalNoteRepository.findAll()
                .stream()
                .map(clinicalNote ->
                        new ClinicalNoteResponse(
                                clinicalNote.id().value(),
                                clinicalNote.subjective(),
                                clinicalNote.objective(),
                                clinicalNote.assessment(),
                                clinicalNote.plan(),
                                clinicalNote.additionalNotes(),
                                clinicalNote.signedAt()
                        )
                )
                .toList();
    }
}
