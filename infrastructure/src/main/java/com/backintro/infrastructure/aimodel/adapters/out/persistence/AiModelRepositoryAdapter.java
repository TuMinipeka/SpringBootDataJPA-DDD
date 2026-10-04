package com.backintro.infrastructure.aimodel.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;
import com.backintro.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;
import com.backintro.infrastructure.aimodel.adapters.out.persistence.mapper.AiModelPersistenceMapper;
import com.backintro.infrastructure.aimodel.adapters.out.persistence.repository.AiModelJpaRepository;

@Repository
@Transactional
public class AiModelRepositoryAdapter implements AiModelRepository {

    private final AiModelJpaRepository repository;
    private final AiModelPersistenceMapper mapper;

    public AiModelRepositoryAdapter(
            AiModelJpaRepository repository,
            AiModelPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AiModel save(AiModel aiModel) {
        AiModelJpaEntity entity = repository
                .findById(aiModel.id().value())
                .map(existing -> {
                    mapper.synchronize(aiModel, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(aiModel));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AiModel> findById(AiModelId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AiModel> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByProviderModelIdAndModelKey(
            AiProviderModelId providerModelId,
            String modelKey
    ) {
        return repository.existsByProviderModelIdAndModelKeyIgnoreCase(
                providerModelId.value(),
                modelKey
        );
    }

    @Override
    public void delete(AiModel aiModel) {
        repository.deleteById(aiModel.id().value());
    }
}
