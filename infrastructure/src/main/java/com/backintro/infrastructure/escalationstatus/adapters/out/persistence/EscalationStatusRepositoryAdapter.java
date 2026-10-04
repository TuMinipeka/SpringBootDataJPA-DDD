package com.backintro.infrastructure.escalationstatus.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;
import com.backintro.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;
import com.backintro.infrastructure.escalationstatus.adapters.out.persistence.mapper.EscalationStatusPersistenceMapper;
import com.backintro.infrastructure.escalationstatus.adapters.out.persistence.repository.EscalationStatusJpaRepository;

@Repository
@Transactional
public class EscalationStatusRepositoryAdapter
        implements EscalationStatusRepository {

    private final EscalationStatusJpaRepository repository;
    private final EscalationStatusPersistenceMapper mapper;

    public EscalationStatusRepositoryAdapter(
            EscalationStatusJpaRepository repository,
            EscalationStatusPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EscalationStatus save(EscalationStatus escalationStatus) {
        EscalationStatusJpaEntity entity = repository
                .findById(escalationStatus.id().value())
                .map(existing -> {
                    mapper.synchronize(escalationStatus, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(escalationStatus));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EscalationStatus> findById(EscalationStatusId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EscalationStatus> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNameStatus(String nameStatus) {
        return repository.existsByNameStatusIgnoreCase(nameStatus);
    }

    @Override
    public void delete(EscalationStatus escalationStatus) {
        repository.deleteById(escalationStatus.id().value());
    }
}
