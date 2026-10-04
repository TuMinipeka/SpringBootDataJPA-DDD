package com.backintro.application.clinicalrecord.usecase;

import java.util.List;

import com.backintro.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.backintro.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class ListClinicalRecordUseCase {

    private final ClinicalRecordRepository clinicalRecordRepository;

    public ListClinicalRecordUseCase(
            ClinicalRecordRepository clinicalRecordRepository
    ) {
        this.clinicalRecordRepository = clinicalRecordRepository;
    }

    public List<ClinicalRecordResponse> execute() {
        return clinicalRecordRepository.findAll()
                .stream()
                .map(clinicalRecord ->
                        new ClinicalRecordResponse(
                                clinicalRecord.id().value(),
                                clinicalRecord.creationDate(),
                                clinicalRecord.recordNumber(),
                                clinicalRecord.openedAt(),
                                clinicalRecord.closedAt()
                        )
                )
                .toList();
    }
}
