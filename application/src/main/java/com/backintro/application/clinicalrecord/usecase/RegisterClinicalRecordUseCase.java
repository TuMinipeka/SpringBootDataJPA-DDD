package com.backintro.application.clinicalrecord.usecase;

import com.backintro.application.clinicalrecord.command.RegisterClinicalRecordCommand;
import com.backintro.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.backintro.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.backintro.application.patient.exception.PatientNotFoundApplicationException;
import com.backintro.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.backintro.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import com.backintro.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;
import com.backintro.domain.patient.port.repository.PatientRepository;

public class RegisterClinicalRecordUseCase {

    private final ClinicalRecordRepository clinicalRecordRepository;
    private final PatientRepository patientRepository;
    private final ClinicalRecordStatusRepository statusRepository;

    public RegisterClinicalRecordUseCase(
            ClinicalRecordRepository clinicalRecordRepository,
            PatientRepository patientRepository,
            ClinicalRecordStatusRepository statusRepository
    ) {
        this.clinicalRecordRepository = clinicalRecordRepository;
        this.patientRepository = patientRepository;
        this.statusRepository = statusRepository;
    }

    public ClinicalRecordResponse execute(
            RegisterClinicalRecordCommand command
    ) {
        patientRepository.findById(command.patientId())
                .orElseThrow(() ->
                        new PatientNotFoundApplicationException(
                                command.patientId().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new ClinicalRecordStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        ClinicalRecord clinicalRecord = ClinicalRecord.register(
                command.patientId(),
                command.recordNumber(),
                command.statusId()
        );

        ClinicalRecord saved = clinicalRecordRepository.save(clinicalRecord);

        return toResponse(saved);
    }

    private ClinicalRecordResponse toResponse(ClinicalRecord clinicalRecord) {
        return new ClinicalRecordResponse(
                clinicalRecord.id().value(),
                clinicalRecord.creationDate(),
                clinicalRecord.recordNumber(),
                clinicalRecord.openedAt(),
                clinicalRecord.closedAt()
        );
    }
}
