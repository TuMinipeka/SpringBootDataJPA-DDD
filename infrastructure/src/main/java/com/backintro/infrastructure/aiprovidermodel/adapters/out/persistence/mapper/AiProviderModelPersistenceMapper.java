package com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.aiprovidermodel.model.aggregate.AiProviderModel;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;
import com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.entity.AiProviderModelJpaEntity;

@Component
public class AiProviderModelPersistenceMapper {

    public AiProviderModel toDomain(AiProviderModelJpaEntity entity) {
        return AiProviderModel.restore(
                new AiProviderModelId(entity.getId()),
                entity.getNameProviderAi(),
                entity.getRazonSocial(),
                entity.getSitioWeb(),
                entity.isActive()
        );
    }

    public AiProviderModelJpaEntity toNewEntity(
            AiProviderModel aiProviderModel
    ) {
        return new AiProviderModelJpaEntity(
                aiProviderModel.id().value(),
                aiProviderModel.nameProviderAi(),
                aiProviderModel.razonSocial(),
                aiProviderModel.sitioWeb(),
                aiProviderModel.active()
        );
    }

    public void synchronize(
            AiProviderModel aiProviderModel,
            AiProviderModelJpaEntity entity
    ) {
        entity.synchronize(
                aiProviderModel.nameProviderAi(),
                aiProviderModel.razonSocial(),
                aiProviderModel.sitioWeb(),
                aiProviderModel.active()
        );
    }
}
