package com.backintro.infrastructure.professional.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.professional.model.aggregate.Professional;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.backintro.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;
import com.backintro.infrastructure.professional.adapters.out.persistence.mapper.ProfessionalPersistenceMapper;
import com.backintro.infrastructure.professional.adapters.out.persistence.repository.ProfessionalJpaRepository;

@ExtendWith(MockitoExtension.class)
class ProfessionalRepositoryAdapterTest {

    @Mock
    private ProfessionalJpaRepository springDataRepository;

    private ProfessionalRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ProfessionalRepositoryAdapter(
                springDataRepository,
                new ProfessionalPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        Professional professional = Professional.register(
                new DocumentTypeId(UUID.randomUUID()),
                "123456789",
                "Jane",
                "Doe",
                new ProfessionalTypeId(UUID.randomUUID()),
                null,
                null,
                null
        );
        when(springDataRepository.findById(professional.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ProfessionalJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Professional saved = adapter.save(professional);

        assertThat(saved.id()).isEqualTo(professional.id());
        assertThat(saved.documentNumber()).isEqualTo("123456789");
        assertThat(saved.firstName()).isEqualTo("Jane");
        assertThat(saved.lastName()).isEqualTo("Doe");
        assertThat(saved.active()).isTrue();
        verify(springDataRepository).save(any(ProfessionalJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        Professional professional = Professional.register(
                new DocumentTypeId(UUID.randomUUID()),
                "123456789",
                "Jane",
                "Doe",
                new ProfessionalTypeId(UUID.randomUUID()),
                null,
                null,
                null
        );

        adapter.delete(professional);

        verify(springDataRepository)
                .deleteById(professional.id().value());
    }
}
