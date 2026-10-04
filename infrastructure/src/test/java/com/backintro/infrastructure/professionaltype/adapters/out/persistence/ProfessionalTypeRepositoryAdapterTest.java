package com.backintro.infrastructure.professionaltype.adapters.out.persistence;

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

import com.backintro.domain.professionaltype.model.aggregate.ProfessionalType;
import com.backintro.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;
import com.backintro.infrastructure.professionaltype.adapters.out.persistence.mapper.ProfessionalTypePersistenceMapper;
import com.backintro.infrastructure.professionaltype.adapters.out.persistence.repository.ProfessionalTypeJpaRepository;

@ExtendWith(MockitoExtension.class)
class ProfessionalTypeRepositoryAdapterTest {

    @Mock
    private ProfessionalTypeJpaRepository springDataRepository;

    private ProfessionalTypeRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ProfessionalTypeRepositoryAdapter(
                springDataRepository,
                new ProfessionalTypePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ProfessionalType professionalType =
                ProfessionalType.register("Psychologist");
        when(springDataRepository.findById(professionalType.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ProfessionalTypeJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProfessionalType saved = adapter.save(professionalType);

        assertThat(saved.id()).isEqualTo(professionalType.id());
        assertThat(saved.name()).isEqualTo("Psychologist");
        verify(springDataRepository).save(any(ProfessionalTypeJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ProfessionalType professionalType =
                ProfessionalType.register("Psychologist");

        adapter.delete(professionalType);

        verify(springDataRepository)
                .deleteById(professionalType.id().value());
    }
}
