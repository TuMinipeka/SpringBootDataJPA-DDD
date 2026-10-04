package com.backintro.application.clinicalrecordstatus.usecase;

import com.backintro.application.clinicalrecordstatus.command.UpdateClinicalRecordStatusCommand;
import com.backintro.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.backintro.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.backintro.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class UpdateClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;

    public UpdateClinicalRecordStatusUseCase(
            ClinicalRecordStatusRepository repository
    ) {
        this.repository = repository;
    }

    public ClinicalRecordStatusResponse execute(
            UpdateClinicalRecordStatusCommand command
    ) {
        var clinicalRecordStatus = repository.findById(command.id())
                .orElseThrow(() ->
                        new ClinicalRecordStatusNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        clinicalRecordStatus.update(command.name(), command.code());

        var updated = repository.save(clinicalRecordStatus);

        return new ClinicalRecordStatusResponse(
                updated.id().value(),
                updated.name(),
                updated.code()
        );
    }
}
