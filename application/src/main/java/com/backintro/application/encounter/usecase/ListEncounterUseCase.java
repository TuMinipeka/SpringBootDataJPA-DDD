package com.backintro.application.encounter.usecase;

import java.util.List;

import com.backintro.application.encounter.dto.EncounterResponse;
import com.backintro.domain.encounter.port.repository.EncounterRepository;

public class ListEncounterUseCase {

    private final EncounterRepository encounterRepository;

    public ListEncounterUseCase(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    public List<EncounterResponse> execute() {
        return encounterRepository.findAll()
                .stream()
                .map(encounter ->
                        new EncounterResponse(
                                encounter.id().value(),
                                encounter.startedAt(),
                                encounter.endedAt(),
                                encounter.reasonForVisit(),
                                encounter.currentCondition()
                        )
                )
                .toList();
    }
}
