package com.backintro.infrastructure.gender.adapters.out.persistence;

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

import com.backintro.domain.gender.model.aggregate.Gender;
import com.backintro.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;
import com.backintro.infrastructure.gender.adapters.out.persistence.mapper.GenderPersistenceMapper;
import com.backintro.infrastructure.gender.adapters.out.persistence.repository.GenderJpaRepository;

@ExtendWith(MockitoExtension.class)
class GenderRepositoryAdapterTest {

    @Mock
    private GenderJpaRepository springDataRepository;

    private GenderRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new GenderRepositoryAdapter(
                springDataRepository,
                new GenderPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        Gender gender = Gender.register("Female");
        when(springDataRepository.findById(gender.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(GenderJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Gender saved = adapter.save(gender);

        assertThat(saved.id()).isEqualTo(gender.id());
        assertThat(saved.description()).isEqualTo("Female");
        verify(springDataRepository).save(any(GenderJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        Gender gender = Gender.register("Female");

        adapter.delete(gender);

        verify(springDataRepository).deleteById(gender.id().value());
    }
}
