package com.backintro.application.stateregion.usecase;

import com.backintro.application.stateregion.dto.StateRegionResponse;
import com.backintro.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;
import com.backintro.domain.stateregion.port.repository.StateRegionRepository;

public class GetStateRegionByIdUseCase {

    private final StateRegionRepository stateRegionRepository;

    public GetStateRegionByIdUseCase(StateRegionRepository stateRegionRepository) {
        this.stateRegionRepository = stateRegionRepository;
    }

    public StateRegionResponse execute(StateRegionId id) {
        var stateRegion = stateRegionRepository.findById(id)
                .orElseThrow(() ->
                        new StateRegionNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new StateRegionResponse(
                stateRegion.id().value(),
                stateRegion.nameRegion(),
                stateRegion.codeRegion()
        );
    }
}
