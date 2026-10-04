package com.backintro.application.clinicalrecordstatus.usecase;

import java.util.List;

import com.backintro.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.backintro.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class ListClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;

    public ListClinicalRecordStatusUseCase(
            ClinicalRecordStatusRepository repository
    ) {
        this.repository = repository;
    }

    public List<ClinicalRecordStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(clinicalRecordStatus ->
                        new ClinicalRecordStatusResponse(
                                clinicalRecordStatus.id().value(),
                                clinicalRecordStatus.name(),
                                clinicalRecordStatus.code()
                        )
                )
                .toList();
    }
}
