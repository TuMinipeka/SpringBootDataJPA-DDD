package com.backintro.application.aimodel.usecase;

import com.backintro.application.aimodel.command.RegisterAiModelCommand;
import com.backintro.application.aimodel.dto.AiModelResponse;
import com.backintro.application.aiprovidermodel.exception.AiProviderModelNotFoundApplicationException;
import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;
import com.backintro.domain.aiprovidermodel.port.repository.AiProviderModelRepository;

public class RegisterAiModelUseCase {

    private final AiModelRepository aiModelRepository;
    private final AiProviderModelRepository aiProviderModelRepository;

    public RegisterAiModelUseCase(
            AiModelRepository aiModelRepository,
            AiProviderModelRepository aiProviderModelRepository
    ) {
        this.aiModelRepository = aiModelRepository;
        this.aiProviderModelRepository = aiProviderModelRepository;
    }

    public AiModelResponse execute(RegisterAiModelCommand command) {
        aiProviderModelRepository.findById(command.providerModelId())
                .orElseThrow(() ->
                        new AiProviderModelNotFoundApplicationException(
                                command.providerModelId().value().toString()
                        )
                );

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
