package com.backintro.infrastructure.aimodel.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;
import com.backintro.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;

@Component
public class AiModelPersistenceMapper {

    public AiModel toDomain(AiModelJpaEntity entity) {
        return AiModel.restore(
                new AiModelId(entity.getId()),
                new AiProviderModelId(entity.getProviderModelId()),
                entity.getNameModel(),
                entity.getModelKey(),
                entity.getInputTokenPrice(),
                entity.getOutputTokenPrice(),
                entity.getMaxTokens(),
                entity.getContextWindow(),
                entity.isActive()
        );
    }

    public AiModelJpaEntity toNewEntity(AiModel aiModel) {
        return new AiModelJpaEntity(
                aiModel.id().value(),
                aiModel.providerModelId().value(),
                aiModel.nameModel(),
                aiModel.modelKey(),
                aiModel.inputTokenPrice(),
                aiModel.outputTokenPrice(),
                aiModel.maxTokens(),
                aiModel.contextWindow(),
                aiModel.active()
        );
    }

    public void synchronize(
            AiModel aiModel,
            AiModelJpaEntity entity
    ) {
        entity.synchronize(
                aiModel.nameModel(),
                aiModel.modelKey(),
                aiModel.inputTokenPrice(),
                aiModel.outputTokenPrice(),
                aiModel.maxTokens(),
                aiModel.contextWindow(),
                aiModel.active()
        );
    }
}
