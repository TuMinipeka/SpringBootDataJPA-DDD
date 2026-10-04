package com.backintro.infrastructure.priority.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.priority.model.aggregate.Priority;
import com.backintro.domain.priority.model.valueobject.PriorityId;
import com.backintro.domain.priority.port.repository.PriorityRepository;
import com.backintro.infrastructure.priority.adapters.out.persistence.entity.PriorityJpaEntity;
import com.backintro.infrastructure.priority.adapters.out.persistence.mapper.PriorityPersistenceMapper;
import com.backintro.infrastructure.priority.adapters.out.persistence.repository.PriorityJpaRepository;

@Repository
@Transactional
public class PriorityRepositoryAdapter
        implements PriorityRepository {

    private final PriorityJpaRepository repository;
    private final PriorityPersistenceMapper mapper;

    public PriorityRepositoryAdapter(
            PriorityJpaRepository repository,
            PriorityPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Priority save(Priority priority) {
        PriorityJpaEntity entity = repository
                .findById(priority.id().value())
                .map(existing -> {
                    mapper.synchronize(priority, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(priority));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Priority> findById(PriorityId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Priority> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNamePriority(String namePriority) {
        return repository.existsByNamePriorityIgnoreCase(namePriority);
    }

    @Override
    public void delete(Priority priority) {
        repository.deleteById(priority.id().value());
    }
}
