package com.backintro.application.encounter.usecase;

import com.backintro.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.backintro.application.encounter.command.RegisterEncounterCommand;
import com.backintro.application.encounter.dto.EncounterResponse;
import com.backintro.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.backintro.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.backintro.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import com.backintro.domain.encounter.model.aggregate.Encounter;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.encountermodality.port.repository.EncounterModalityRepository;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;
import com.backintro.domain.encountertype.port.repository.EncounterTypeRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class RegisterEncounterUseCase {

    private final EncounterRepository encounterRepository;
    private final ClinicalRecordRepository clinicalRecordRepository;
    private final ProfessionalRepository professionalRepository;
    private final EncounterTypeRepository encounterTypeRepository;
    private final EncounterModalityRepository modalityRepository;
    private final EncounterStatusRepository statusRepository;

    public RegisterEncounterUseCase(
            EncounterRepository encounterRepository,
            ClinicalRecordRepository clinicalRecordRepository,
            ProfessionalRepository professionalRepository,
            EncounterTypeRepository encounterTypeRepository,
            EncounterModalityRepository modalityRepository,
            EncounterStatusRepository statusRepository
    ) {
        this.encounterRepository = encounterRepository;
        this.clinicalRecordRepository = clinicalRecordRepository;
        this.professionalRepository = professionalRepository;
        this.encounterTypeRepository = encounterTypeRepository;
        this.modalityRepository = modalityRepository;
        this.statusRepository = statusRepository;
    }

    public EncounterResponse execute(RegisterEncounterCommand command) {
        clinicalRecordRepository.findById(command.clinicalRecordId())
                .orElseThrow(() ->
                        new ClinicalRecordNotFoundApplicationException(
                                command.clinicalRecordId().value().toString()
                        )
                );
        professionalRepository.findById(command.professionalId())
                .orElseThrow(() ->
                        new ProfessionalNotFoundApplicationException(
                                command.professionalId().value().toString()
                        )
                );
        encounterTypeRepository.findById(command.encounterTypeId())
                .orElseThrow(() ->
                        new EncounterTypeNotFoundApplicationException(
                                command.encounterTypeId().value().toString()
                        )
                );
        modalityRepository.findById(command.modalityId())
                .orElseThrow(() ->
                        new EncounterModalityNotFoundApplicationException(
                                command.modalityId().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new EncounterStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        Encounter encounter = Encounter.register(
                command.clinicalRecordId(),
                command.professionalId(),
                command.encounterTypeId(),
                command.startedAt(),
                command.reasonForVisit(),
                command.currentCondition(),
                command.modalityId(),
                command.statusId()
        );

        return toResponse(encounterRepository.save(encounter));
    }

    private EncounterResponse toResponse(Encounter encounter) {
        return new EncounterResponse(
                encounter.id().value(),
                encounter.startedAt(),
                encounter.endedAt(),
                encounter.reasonForVisit(),
                encounter.currentCondition()
        );
    }
}
