package com.backintro.application.treatmentstatus.usecase;

import com.backintro.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.backintro.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class GetTreatmentStatusByIdUseCase {

    private final TreatmentStatusRepository treatmentStatusRepository;

    public GetTreatmentStatusByIdUseCase(
            TreatmentStatusRepository treatmentStatusRepository
    ) {
        this.treatmentStatusRepository = treatmentStatusRepository;
    }

    public TreatmentStatusResponse execute(TreatmentStatusId id) {
        var treatmentStatus = treatmentStatusRepository.findById(id)
                .orElseThrow(() ->
                        new TreatmentStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new TreatmentStatusResponse(
                treatmentStatus.id().value(),
                treatmentStatus.name(),
                treatmentStatus.code()
        );
    }
}
