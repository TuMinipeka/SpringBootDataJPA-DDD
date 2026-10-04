package com.backintro.domain.clinicalnote.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.backintro.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public interface ClinicalNoteRepository {

    ClinicalNote save(ClinicalNote clinicalNote);

    Optional<ClinicalNote> findById(ClinicalNoteId id);

    List<ClinicalNote> findAll();

    void delete(ClinicalNote clinicalNote);
}
