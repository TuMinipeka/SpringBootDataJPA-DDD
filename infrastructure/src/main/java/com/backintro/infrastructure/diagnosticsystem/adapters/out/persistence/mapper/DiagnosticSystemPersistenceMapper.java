package com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;

@Component
public class DiagnosticSystemPersistenceMapper {

    public DiagnosticSystem toDomain(DiagnosticSystemJpaEntity entity) {
        return DiagnosticSystem.restore(
                new DiagnosticSystemId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive(),
                entity.getVersion()
        );
    }

    public DiagnosticSystemJpaEntity toNewEntity(
            DiagnosticSystem diagnosticSystem
    ) {
        return new DiagnosticSystemJpaEntity(
                diagnosticSystem.id().value(),
                diagnosticSystem.name(),
                diagnosticSystem.code(),
                diagnosticSystem.active(),
                diagnosticSystem.version()
        );
    }

    public void synchronize(
            DiagnosticSystem diagnosticSystem,
            DiagnosticSystemJpaEntity entity
    ) {
        entity.synchronize(
                diagnosticSystem.name(),
                diagnosticSystem.code(),
                diagnosticSystem.active(),
                diagnosticSystem.version()
        );
    }
}
