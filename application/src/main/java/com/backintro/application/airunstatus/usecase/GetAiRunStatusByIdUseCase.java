package com.backintro.application.airunstatus.usecase;

import com.backintro.application.airunstatus.dto.AiRunStatusResponse;
import com.backintro.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;

public class GetAiRunStatusByIdUseCase {

    private final AiRunStatusRepository aiRunStatusRepository;

    public GetAiRunStatusByIdUseCase(
            AiRunStatusRepository aiRunStatusRepository
    ) {
        this.aiRunStatusRepository = aiRunStatusRepository;
    }

    public AiRunStatusResponse execute(AiRunStatusId id) {
        var aiRunStatus = aiRunStatusRepository.findById(id)
                .orElseThrow(() ->
                        new AiRunStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new AiRunStatusResponse(
                aiRunStatus.id().value(),
                aiRunStatus.nameStatus()
        );
    }
}
