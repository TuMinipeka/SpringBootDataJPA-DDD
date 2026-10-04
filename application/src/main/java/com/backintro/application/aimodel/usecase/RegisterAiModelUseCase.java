package com.backintro.application.aimodel.usecase;

import com.backintro.application.aimodel.command.RegisterAiModelCommand;
import com.backintro.application.aimodel.dto.AiModelResponse;
import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;

public class RegisterAiModelUseCase {

    private final AiModelRepository aiModelRepository;

    public RegisterAiModelUseCase(AiModelRepository aiModelRepository) {
        this.aiModelRepository = aiModelRepository;
    }

    public AiModelResponse execute(RegisterAiModelCommand command) {
        AiModel aiModel = AiModel.register(
                command.providerModelId(),
                command.nameModel(),
                command.modelKey(),
                command.inputTokenPrice(),
                command.outputTokenPrice(),
                command.maxTokens(),
                command.contextWindow()
        );
        AiModel saved = aiModelRepository.save(aiModel);

        return toResponse(saved);
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
