package com.backintro.infrastructure.consenttype.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.consenttype.model.aggregate.ConsentType;
import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;
import com.backintro.domain.consenttype.port.repository.ConsentTypeRepository;
import com.backintro.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;
import com.backintro.infrastructure.consenttype.adapters.out.persistence.mapper.ConsentTypePersistenceMapper;
import com.backintro.infrastructure.consenttype.adapters.out.persistence.repository.ConsentTypeJpaRepository;

@Repository
@Transactional
public class ConsentTypeRepositoryAdapter
        implements ConsentTypeRepository {

    private final ConsentTypeJpaRepository repository;
    private final ConsentTypePersistenceMapper mapper;

    public ConsentTypeRepositoryAdapter(
            ConsentTypeJpaRepository repository,
            ConsentTypePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ConsentType save(ConsentType consentType) {
        ConsentTypeJpaEntity entity = repository
                .findById(consentType.id().value())
                .map(existing -> {
                    mapper.synchronize(consentType, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(consentType));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ConsentType> findById(ConsentTypeId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsentType> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return repository.existsByCodeIgnoreCase(code);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return repository.existsByNameIgnoreCase(name);
    }

    @Override
    public void delete(ConsentType consentType) {
        repository.deleteById(consentType.id().value());
    }
}
