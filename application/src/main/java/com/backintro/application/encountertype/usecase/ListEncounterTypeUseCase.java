package com.backintro.application.encountertype.usecase;

import java.util.List;

import com.backintro.application.encountertype.dto.EncounterTypeResponse;
import com.backintro.domain.encountertype.port.repository.EncounterTypeRepository;

public class ListEncounterTypeUseCase {

    private final EncounterTypeRepository encounterTypeRepository;

    public ListEncounterTypeUseCase(
            EncounterTypeRepository encounterTypeRepository
    ) {
        this.encounterTypeRepository = encounterTypeRepository;
    }

    public List<EncounterTypeResponse> execute() {
        return encounterTypeRepository.findAll()
                .stream()
                .map(encounterType ->
                        new EncounterTypeResponse(
                                encounterType.id().value(),
                                encounterType.name(),
                                encounterType.code()
                        )
                )
                .toList();
    }
}
