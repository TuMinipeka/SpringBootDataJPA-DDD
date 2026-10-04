package com.backintro.application.clinicalrecordstatus.usecase;

import com.backintro.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.backintro.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class GetClinicalRecordStatusByIdUseCase {

    private final ClinicalRecordStatusRepository repository;

    public GetClinicalRecordStatusByIdUseCase(
            ClinicalRecordStatusRepository repository
    ) {
        this.repository = repository;
    }

    public ClinicalRecordStatusResponse execute(ClinicalRecordStatusId id) {
        var clinicalRecordStatus = repository.findById(id)
                .orElseThrow(() ->
                        new ClinicalRecordStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new ClinicalRecordStatusResponse(
                clinicalRecordStatus.id().value(),
                clinicalRecordStatus.name(),
                clinicalRecordStatus.code()
        );
    }
}
