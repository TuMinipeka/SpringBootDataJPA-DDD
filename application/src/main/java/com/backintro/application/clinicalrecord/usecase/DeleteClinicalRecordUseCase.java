package com.backintro.application.clinicalrecord.usecase;

import java.time.LocalDateTime;

import com.backintro.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.backintro.domain.clinicalrecord.event.ClinicalRecordDeletedEvent;
import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class DeleteClinicalRecordUseCase {

    private final ClinicalRecordRepository clinicalRecordRepository;

    public DeleteClinicalRecordUseCase(
            ClinicalRecordRepository clinicalRecordRepository
    ) {
        this.clinicalRecordRepository = clinicalRecordRepository;
    }

    public ClinicalRecordDeletedEvent execute(ClinicalRecordId id) {
        var clinicalRecord = clinicalRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ClinicalRecordNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        clinicalRecordRepository.delete(clinicalRecord);

        return new ClinicalRecordDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
