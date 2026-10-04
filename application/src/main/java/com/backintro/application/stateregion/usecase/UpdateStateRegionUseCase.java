package com.backintro.application.stateregion.usecase;

import com.backintro.application.stateregion.command.UpdateStateRegionCommand;
import com.backintro.application.stateregion.dto.StateRegionResponse;
import com.backintro.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.backintro.domain.stateregion.port.repository.StateRegionRepository;

public class UpdateStateRegionUseCase {

    private final StateRegionRepository stateRegionRepository;

    public UpdateStateRegionUseCase(StateRegionRepository stateRegionRepository) {
        this.stateRegionRepository = stateRegionRepository;
    }

    public StateRegionResponse execute(UpdateStateRegionCommand command) {
        var stateRegion = stateRegionRepository.findById(command.id())
                .orElseThrow(() ->
                        new StateRegionNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        stateRegion.update(
                command.nameRegion(),
                command.codeRegion()
        );

        var updated = stateRegionRepository.save(stateRegion);

        return new StateRegionResponse(
                updated.id().value(),
                updated.nameRegion(),
                updated.codeRegion()
        );
    }
}
