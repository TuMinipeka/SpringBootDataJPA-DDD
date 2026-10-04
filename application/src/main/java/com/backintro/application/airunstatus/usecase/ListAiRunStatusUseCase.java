package com.backintro.application.airunstatus.usecase;

import java.util.List;

import com.backintro.application.airunstatus.dto.AiRunStatusResponse;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;

public class ListAiRunStatusUseCase {

    private final AiRunStatusRepository aiRunStatusRepository;

    public ListAiRunStatusUseCase(
            AiRunStatusRepository aiRunStatusRepository
    ) {
        this.aiRunStatusRepository = aiRunStatusRepository;
    }

    public List<AiRunStatusResponse> execute() {
        return aiRunStatusRepository.findAll()
                .stream()
                .map(aiRunStatus ->
                        new AiRunStatusResponse(
                                aiRunStatus.id().value(),
                                aiRunStatus.nameStatus()
                        )
                )
                .toList();
    }
}
