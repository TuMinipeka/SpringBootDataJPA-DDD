package com.backintro.application.treatmentstatus.usecase;

import java.time.LocalDateTime;

import com.backintro.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.backintro.domain.treatmentstatus.event.TreatmentStatusDeletedEvent;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class DeleteTreatmentStatusUseCase {

    private final TreatmentStatusRepository treatmentStatusRepository;

    public DeleteTreatmentStatusUseCase(
            TreatmentStatusRepository treatmentStatusRepository
    ) {
        this.treatmentStatusRepository = treatmentStatusRepository;
    }

    public TreatmentStatusDeletedEvent execute(TreatmentStatusId id) {
        var treatmentStatus = treatmentStatusRepository.findById(id)
                .orElseThrow(() ->
                        new TreatmentStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        treatmentStatusRepository.delete(treatmentStatus);

        return new TreatmentStatusDeletedEvent(id, LocalDateTime.now());
    }
}
