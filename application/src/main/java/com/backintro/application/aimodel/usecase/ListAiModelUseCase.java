package com.backintro.application.aimodel.usecase;

import java.util.List;

import com.backintro.application.aimodel.dto.AiModelResponse;
import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;

public class ListAiModelUseCase {

    private final AiModelRepository aiModelRepository;

    public ListAiModelUseCase(AiModelRepository aiModelRepository) {
        this.aiModelRepository = aiModelRepository;
    }

    public List<AiModelResponse> execute() {
        return aiModelRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
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
