package com.backintro.application.airunstatus.usecase;

import com.backintro.application.airunstatus.command.RegisterAiRunStatusCommand;
import com.backintro.application.airunstatus.dto.AiRunStatusResponse;
import com.backintro.domain.airunstatus.model.aggregate.AiRunStatus;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;

public class RegisterAiRunStatusUseCase {

    private final AiRunStatusRepository aiRunStatusRepository;

    public RegisterAiRunStatusUseCase(
            AiRunStatusRepository aiRunStatusRepository
    ) {
        this.aiRunStatusRepository = aiRunStatusRepository;
    }

    public AiRunStatusResponse execute(
            RegisterAiRunStatusCommand command
    ) {
        AiRunStatus aiRunStatus =
                AiRunStatus.register(command.nameStatus());
        AiRunStatus saved =
                aiRunStatusRepository.save(aiRunStatus);

        return new AiRunStatusResponse(
                saved.id().value(),
                saved.nameStatus()
        );
    }
}
