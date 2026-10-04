package com.backintro.application.stateregion.usecase;

import java.time.LocalDateTime;

import com.backintro.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.backintro.domain.stateregion.event.StateRegionDeletedEvent;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;
import com.backintro.domain.stateregion.port.repository.StateRegionRepository;

public class DeleteStateRegionUseCase {

    private final StateRegionRepository stateRegionRepository;

    public DeleteStateRegionUseCase(StateRegionRepository stateRegionRepository) {
        this.stateRegionRepository = stateRegionRepository;
    }

    public StateRegionDeletedEvent execute(StateRegionId id) {
        var stateRegion = stateRegionRepository.findById(id)
                .orElseThrow(() ->
                        new StateRegionNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        stateRegionRepository.delete(stateRegion);

        return new StateRegionDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
