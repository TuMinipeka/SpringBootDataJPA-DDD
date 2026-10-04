package com.backintro.application.clinicalrecordstatus.usecase;

import java.time.LocalDateTime;

import com.backintro.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.backintro.domain.clinicalrecordstatus.event.ClinicalRecordStatusDeletedEvent;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class DeleteClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;

    public DeleteClinicalRecordStatusUseCase(
            ClinicalRecordStatusRepository repository
    ) {
        this.repository = repository;
    }

    public ClinicalRecordStatusDeletedEvent execute(ClinicalRecordStatusId id) {
        var clinicalRecordStatus = repository.findById(id)
                .orElseThrow(() ->
                        new ClinicalRecordStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        repository.delete(clinicalRecordStatus);

        return new ClinicalRecordStatusDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
