package com.backintro.application.stateregion.usecase;

import java.util.List;

import com.backintro.application.stateregion.dto.StateRegionResponse;
import com.backintro.domain.stateregion.port.repository.StateRegionRepository;

public class ListStateRegionUseCase {

    private final StateRegionRepository stateRegionRepository;

    public ListStateRegionUseCase(StateRegionRepository stateRegionRepository) {
        this.stateRegionRepository = stateRegionRepository;
    }

    public List<StateRegionResponse> execute() {
        return stateRegionRepository.findAll()
                .stream()
                .map(stateRegion ->
                        new StateRegionResponse(
                                stateRegion.id().value(),
                                stateRegion.nameRegion(),
                                stateRegion.codeRegion()
                        )
                )
                .toList();
    }
}
