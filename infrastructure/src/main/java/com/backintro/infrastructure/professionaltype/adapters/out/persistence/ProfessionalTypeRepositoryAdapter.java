package com.backintro.infrastructure.professionaltype.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.professionaltype.model.aggregate.ProfessionalType;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.backintro.domain.professionaltype.port.repository.ProfessionalTypeRepository;
import com.backintro.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;
import com.backintro.infrastructure.professionaltype.adapters.out.persistence.mapper.ProfessionalTypePersistenceMapper;
import com.backintro.infrastructure.professionaltype.adapters.out.persistence.repository.ProfessionalTypeJpaRepository;

@Repository
@Transactional
public class ProfessionalTypeRepositoryAdapter
        implements ProfessionalTypeRepository {

    private final ProfessionalTypeJpaRepository repository;
    private final ProfessionalTypePersistenceMapper mapper;

    public ProfessionalTypeRepositoryAdapter(
            ProfessionalTypeJpaRepository repository,
            ProfessionalTypePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProfessionalType save(ProfessionalType professionalType) {
        ProfessionalTypeJpaEntity entity = repository
                .findById(professionalType.id().value())
                .map(existing -> {
                    mapper.synchronize(professionalType, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(professionalType));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfessionalType> findById(ProfessionalTypeId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfessionalType> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return repository.existsByNameIgnoreCase(name);
    }

    @Override
    public void delete(ProfessionalType professionalType) {
        repository.deleteById(professionalType.id().value());
    }
}
