package com.backintro.application.treatmentstatus.usecase;

import java.util.List;

import com.backintro.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class ListTreatmentStatusUseCase {

    private final TreatmentStatusRepository treatmentStatusRepository;

    public ListTreatmentStatusUseCase(
            TreatmentStatusRepository treatmentStatusRepository
    ) {
        this.treatmentStatusRepository = treatmentStatusRepository;
    }

    public List<TreatmentStatusResponse> execute() {
        return treatmentStatusRepository.findAll()
                .stream()
                .map(treatmentStatus ->
                        new TreatmentStatusResponse(
                                treatmentStatus.id().value(),
                                treatmentStatus.name(),
                                treatmentStatus.code()
                        )
                )
                .toList();
    }
}
