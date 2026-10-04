package com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;
import com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.mapper.DiagnosticSystemPersistenceMapper;
import com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.repository.DiagnosticSystemJpaRepository;

@ExtendWith(MockitoExtension.class)
class DiagnosticSystemRepositoryAdapterTest {

    @Mock
    private DiagnosticSystemJpaRepository springDataRepository;

    private DiagnosticSystemRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new DiagnosticSystemRepositoryAdapter(
                springDataRepository,
                new DiagnosticSystemPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        DiagnosticSystem diagnosticSystem = newDiagnosticSystem();
        when(springDataRepository.findById(diagnosticSystem.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(DiagnosticSystemJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        DiagnosticSystem saved = adapter.save(diagnosticSystem);

        assertThat(saved.id()).isEqualTo(diagnosticSystem.id());
        assertThat(saved.name()).isEqualTo("International Classification of Diseases");
        assertThat(saved.code()).isEqualTo("ICD");
        assertThat(saved.version())
                .isEqualTo("11");
        verify(springDataRepository).save(any(DiagnosticSystemJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        DiagnosticSystem diagnosticSystem = newDiagnosticSystem();

        adapter.delete(diagnosticSystem);

        verify(springDataRepository)
                .deleteById(diagnosticSystem.id().value());
    }

    private DiagnosticSystem newDiagnosticSystem() {
        return DiagnosticSystem.register(
                "International Classification of Diseases",
                "ICD",
                "11"
        );
    }
}
