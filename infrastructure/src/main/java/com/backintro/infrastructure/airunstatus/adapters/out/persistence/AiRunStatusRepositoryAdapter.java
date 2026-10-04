package com.backintro.infrastructure.airunstatus.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.airunstatus.model.aggregate.AiRunStatus;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;
import com.backintro.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;
import com.backintro.infrastructure.airunstatus.adapters.out.persistence.mapper.AiRunStatusPersistenceMapper;
import com.backintro.infrastructure.airunstatus.adapters.out.persistence.repository.AiRunStatusJpaRepository;

@Repository
@Transactional
public class AiRunStatusRepositoryAdapter
        implements AiRunStatusRepository {

    private final AiRunStatusJpaRepository repository;
    private final AiRunStatusPersistenceMapper mapper;

    public AiRunStatusRepositoryAdapter(
            AiRunStatusJpaRepository repository,
            AiRunStatusPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AiRunStatus save(AiRunStatus aiRunStatus) {
        AiRunStatusJpaEntity entity = repository
                .findById(aiRunStatus.id().value())
                .map(existing -> {
                    mapper.synchronize(aiRunStatus, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(aiRunStatus));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AiRunStatus> findById(AiRunStatusId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AiRunStatus> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNameStatus(String nameStatus) {
        return repository.existsByNameStatusIgnoreCase(nameStatus);
    }

    @Override
    public void delete(AiRunStatus aiRunStatus) {
        repository.deleteById(aiRunStatus.id().value());
    }
}
