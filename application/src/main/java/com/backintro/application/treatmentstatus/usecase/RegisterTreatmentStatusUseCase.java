package com.backintro.application.treatmentstatus.usecase;

import com.backintro.application.treatmentstatus.command.RegisterTreatmentStatusCommand;
import com.backintro.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.backintro.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class RegisterTreatmentStatusUseCase {

    private final TreatmentStatusRepository treatmentStatusRepository;

    public RegisterTreatmentStatusUseCase(
            TreatmentStatusRepository treatmentStatusRepository
    ) {
        this.treatmentStatusRepository = treatmentStatusRepository;
    }

    public TreatmentStatusResponse execute(
            RegisterTreatmentStatusCommand command
    ) {
        TreatmentStatus treatmentStatus = TreatmentStatus.register(
                command.name(),
                command.code()
        );

        TreatmentStatus saved =
                treatmentStatusRepository.save(treatmentStatus);

        return new TreatmentStatusResponse(
                saved.id().value(),
                saved.name(),
                saved.code()
        );
    }
}
