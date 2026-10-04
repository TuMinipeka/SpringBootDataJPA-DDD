package com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.backintro.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;
import com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;
import com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.mapper.DiagnosticSystemPersistenceMapper;
import com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.repository.DiagnosticSystemJpaRepository;

@Repository
@Transactional
public class DiagnosticSystemRepositoryAdapter
        implements DiagnosticSystemRepository {

    private final DiagnosticSystemJpaRepository repository;
    private final DiagnosticSystemPersistenceMapper mapper;

    public DiagnosticSystemRepositoryAdapter(
            DiagnosticSystemJpaRepository repository,
            DiagnosticSystemPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DiagnosticSystem save(DiagnosticSystem diagnosticSystem) {
        DiagnosticSystemJpaEntity entity = repository
                .findById(diagnosticSystem.id().value())
                .map(existing -> {
                    mapper.synchronize(diagnosticSystem, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(diagnosticSystem));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DiagnosticSystem> findById(DiagnosticSystemId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiagnosticSystem> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return repository.existsByCodeIgnoreCase(code);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNameAndVersion(String name, String version) {
        return repository.existsByNameIgnoreCaseAndVersionIgnoreCase(
                name,
                version
        );
    }

    @Override
    public void delete(DiagnosticSystem diagnosticSystem) {
        repository.deleteById(diagnosticSystem.id().value());
    }
}
