package com.backintro.application.clinicalrecord.usecase;

import com.backintro.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.backintro.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class GetClinicalRecordByIdUseCase {

    private final ClinicalRecordRepository clinicalRecordRepository;

    public GetClinicalRecordByIdUseCase(
            ClinicalRecordRepository clinicalRecordRepository
    ) {
        this.clinicalRecordRepository = clinicalRecordRepository;
    }

    public ClinicalRecordResponse execute(ClinicalRecordId id) {
        var clinicalRecord = clinicalRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ClinicalRecordNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new ClinicalRecordResponse(
                clinicalRecord.id().value(),
                clinicalRecord.creationDate(),
                clinicalRecord.recordNumber(),
                clinicalRecord.openedAt(),
                clinicalRecord.closedAt()
        );
    }
}
