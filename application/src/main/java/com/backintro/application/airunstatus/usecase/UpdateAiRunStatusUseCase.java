package com.backintro.application.airunstatus.usecase;

import com.backintro.application.airunstatus.command.UpdateAiRunStatusCommand;
import com.backintro.application.airunstatus.dto.AiRunStatusResponse;
import com.backintro.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;

public class UpdateAiRunStatusUseCase {

    private final AiRunStatusRepository aiRunStatusRepository;

    public UpdateAiRunStatusUseCase(
            AiRunStatusRepository aiRunStatusRepository
    ) {
        this.aiRunStatusRepository = aiRunStatusRepository;
    }

    public AiRunStatusResponse execute(
            UpdateAiRunStatusCommand command
    ) {
        var aiRunStatus = aiRunStatusRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new AiRunStatusNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        aiRunStatus.update(command.nameStatus());

        var updated = aiRunStatusRepository.save(aiRunStatus);

        return new AiRunStatusResponse(
                updated.id().value(),
                updated.nameStatus()
        );
    }
}
