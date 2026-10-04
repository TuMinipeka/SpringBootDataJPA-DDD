package com.backintro.application.treatmentstatus.usecase;

import com.backintro.application.treatmentstatus.command.UpdateTreatmentStatusCommand;
import com.backintro.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.backintro.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class UpdateTreatmentStatusUseCase {

    private final TreatmentStatusRepository treatmentStatusRepository;

    public UpdateTreatmentStatusUseCase(
            TreatmentStatusRepository treatmentStatusRepository
    ) {
        this.treatmentStatusRepository = treatmentStatusRepository;
    }

    public TreatmentStatusResponse execute(
            UpdateTreatmentStatusCommand command
    ) {
        var treatmentStatus = treatmentStatusRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new TreatmentStatusNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        treatmentStatus.update(command.name(), command.code());

        var updated = treatmentStatusRepository.save(treatmentStatus);

        return new TreatmentStatusResponse(
                updated.id().value(),
                updated.name(),
                updated.code()
        );
    }
}
