package com.backintro.application.aimodel.usecase;

import com.backintro.application.aimodel.dto.AiModelResponse;
import com.backintro.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;

public class GetAiModelByIdUseCase {

    private final AiModelRepository aiModelRepository;

    public GetAiModelByIdUseCase(AiModelRepository aiModelRepository) {
        this.aiModelRepository = aiModelRepository;
    }

    public AiModelResponse execute(AiModelId id) {
        var aiModel = aiModelRepository.findById(id)
                .orElseThrow(() ->
                        new AiModelNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(aiModel);
    }

    private AiModelResponse toResponse(AiModel aiModel) {
        return new AiModelResponse(
                aiModel.id().value(),
                aiModel.providerModelId().value(),
                aiModel.nameModel(),
                aiModel.modelKey(),
                aiModel.inputTokenPrice(),
                aiModel.outputTokenPrice(),
                aiModel.maxTokens(),
                aiModel.contextWindow()
        );
    }
}
