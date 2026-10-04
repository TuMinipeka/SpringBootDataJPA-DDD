package com.backintro.application.encountermodality.usecase;

import java.util.List;

import com.backintro.application.encountermodality.dto.EncounterModalityResponse;
import com.backintro.domain.encountermodality.port.repository.EncounterModalityRepository;

public class ListEncounterModalityUseCase {

    private final EncounterModalityRepository encounterModalityRepository;

    public ListEncounterModalityUseCase(
            EncounterModalityRepository encounterModalityRepository
    ) {
        this.encounterModalityRepository = encounterModalityRepository;
    }

    public List<EncounterModalityResponse> execute() {
        return encounterModalityRepository.findAll()
                .stream()
                .map(encounterModality ->
                        new EncounterModalityResponse(
                                encounterModality.id().value(),
                                encounterModality.name(),
                                encounterModality.code()
                        )
                )
                .toList();
    }
}
