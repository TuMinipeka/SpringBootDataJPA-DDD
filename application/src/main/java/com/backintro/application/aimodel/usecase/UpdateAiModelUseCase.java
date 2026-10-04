package com.backintro.application.aimodel.usecase;

import com.backintro.application.aimodel.command.UpdateAiModelCommand;
import com.backintro.application.aimodel.dto.AiModelResponse;
import com.backintro.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;

public class UpdateAiModelUseCase {

    private final AiModelRepository aiModelRepository;

    public UpdateAiModelUseCase(AiModelRepository aiModelRepository) {
        this.aiModelRepository = aiModelRepository;
    }

    public AiModelResponse execute(UpdateAiModelCommand command) {
        var aiModel = aiModelRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new AiModelNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        aiModel.update(
                command.nameModel(),
                command.modelKey(),
                command.inputTokenPrice(),
                command.outputTokenPrice(),
                command.maxTokens(),
                command.contextWindow()
        );

        return toResponse(aiModelRepository.save(aiModel));
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
