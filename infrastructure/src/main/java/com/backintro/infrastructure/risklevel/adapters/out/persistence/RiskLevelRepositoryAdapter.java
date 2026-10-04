package com.backintro.infrastructure.risklevel.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.risklevel.model.aggregate.RiskLevel;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;
import com.backintro.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;
import com.backintro.infrastructure.risklevel.adapters.out.persistence.mapper.RiskLevelPersistenceMapper;
import com.backintro.infrastructure.risklevel.adapters.out.persistence.repository.RiskLevelJpaRepository;

@Repository
@Transactional
public class RiskLevelRepositoryAdapter implements RiskLevelRepository {

    private final RiskLevelJpaRepository repository;
    private final RiskLevelPersistenceMapper mapper;

    public RiskLevelRepositoryAdapter(
            RiskLevelJpaRepository repository,
            RiskLevelPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RiskLevel save(RiskLevel riskLevel) {
        RiskLevelJpaEntity entity = repository
                .findById(riskLevel.id().value())
                .map(existing -> {
                    mapper.synchronize(riskLevel, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(riskLevel));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiskLevel> findById(RiskLevelId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiskLevel> findAll() {
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
    public void delete(RiskLevel riskLevel) {
        repository.deleteById(riskLevel.id().value());
    }
}
