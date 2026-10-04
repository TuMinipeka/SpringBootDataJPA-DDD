package com.backintro.infrastructure.priority.adapters.out.persistence;

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

import com.backintro.domain.priority.model.aggregate.Priority;
import com.backintro.infrastructure.priority.adapters.out.persistence.entity.PriorityJpaEntity;
import com.backintro.infrastructure.priority.adapters.out.persistence.mapper.PriorityPersistenceMapper;
import com.backintro.infrastructure.priority.adapters.out.persistence.repository.PriorityJpaRepository;

@ExtendWith(MockitoExtension.class)
class PriorityRepositoryAdapterTest {

    @Mock
    private PriorityJpaRepository springDataRepository;

    private PriorityRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new PriorityRepositoryAdapter(
                springDataRepository,
                new PriorityPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        Priority priority =
                Priority.register("High");
        when(springDataRepository.findById(priority.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(PriorityJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Priority saved = adapter.save(priority);

        assertThat(saved.id()).isEqualTo(priority.id());
        assertThat(saved.namePriority()).isEqualTo("High");
        verify(springDataRepository).save(any(PriorityJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        Priority priority =
                Priority.register("High");

        adapter.delete(priority);

        verify(springDataRepository)
                .deleteById(priority.id().value());
    }
}
