package com.backintro.application.clinicalrecord.usecase;

import com.backintro.application.clinicalrecord.command.UpdateClinicalRecordCommand;
import com.backintro.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.backintro.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.backintro.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.backintro.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import com.backintro.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class UpdateClinicalRecordUseCase {

    private final ClinicalRecordRepository clinicalRecordRepository;
    private final ClinicalRecordStatusRepository statusRepository;

    public UpdateClinicalRecordUseCase(
            ClinicalRecordRepository clinicalRecordRepository,
            ClinicalRecordStatusRepository statusRepository
    ) {
        this.clinicalRecordRepository = clinicalRecordRepository;
        this.statusRepository = statusRepository;
    }

    public ClinicalRecordResponse execute(UpdateClinicalRecordCommand command) {
        var clinicalRecord = clinicalRecordRepository.findById(command.id())
                .orElseThrow(() ->
                        new ClinicalRecordNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new ClinicalRecordStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        clinicalRecord.update(
                command.recordNumber(),
                command.closedAt(),
                command.statusId()
        );

        var updated = clinicalRecordRepository.save(clinicalRecord);

        return new ClinicalRecordResponse(
                updated.id().value(),
                updated.creationDate(),
                updated.recordNumber(),
                updated.openedAt(),
                updated.closedAt()
        );
    }
}
