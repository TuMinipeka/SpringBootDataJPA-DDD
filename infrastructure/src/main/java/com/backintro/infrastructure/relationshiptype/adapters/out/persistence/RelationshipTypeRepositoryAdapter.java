package com.backintro.infrastructure.relationshiptype.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.relationshiptype.model.aggregate.RelationshipType;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.backintro.domain.relationshiptype.port.repository.RelationshipTypeRepository;
import com.backintro.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;
import com.backintro.infrastructure.relationshiptype.adapters.out.persistence.mapper.RelationshipTypePersistenceMapper;
import com.backintro.infrastructure.relationshiptype.adapters.out.persistence.repository.RelationshipTypeJpaRepository;

@Repository
@Transactional
public class RelationshipTypeRepositoryAdapter
        implements RelationshipTypeRepository {

    private final RelationshipTypeJpaRepository repository;
    private final RelationshipTypePersistenceMapper mapper;

    public RelationshipTypeRepositoryAdapter(
            RelationshipTypeJpaRepository repository,
            RelationshipTypePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RelationshipType save(RelationshipType relationshipType) {
        RelationshipTypeJpaEntity entity = repository
                .findById(relationshipType.id().value())
                .map(existing -> {
                    mapper.synchronize(relationshipType, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(relationshipType));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelationshipType> findById(RelationshipTypeId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RelationshipType> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDescription(String description) {
        return repository.existsByDescriptionIgnoreCase(description);
    }

    @Override
    public void delete(RelationshipType relationshipType) {
        repository.deleteById(relationshipType.id().value());
    }
}
