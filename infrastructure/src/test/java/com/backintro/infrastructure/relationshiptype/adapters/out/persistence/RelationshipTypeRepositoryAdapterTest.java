package com.backintro.infrastructure.relationshiptype.adapters.out.persistence;

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

import com.backintro.domain.relationshiptype.model.aggregate.RelationshipType;
import com.backintro.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;
import com.backintro.infrastructure.relationshiptype.adapters.out.persistence.mapper.RelationshipTypePersistenceMapper;
import com.backintro.infrastructure.relationshiptype.adapters.out.persistence.repository.RelationshipTypeJpaRepository;

@ExtendWith(MockitoExtension.class)
class RelationshipTypeRepositoryAdapterTest {

    @Mock
    private RelationshipTypeJpaRepository springDataRepository;

    private RelationshipTypeRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new RelationshipTypeRepositoryAdapter(
                springDataRepository,
                new RelationshipTypePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        RelationshipType relationshipType =
                RelationshipType.register("Parent");
        when(springDataRepository.findById(relationshipType.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(RelationshipTypeJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RelationshipType saved = adapter.save(relationshipType);

        assertThat(saved.id()).isEqualTo(relationshipType.id());
        assertThat(saved.description()).isEqualTo("Parent");
        verify(springDataRepository).save(any(RelationshipTypeJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        RelationshipType relationshipType =
                RelationshipType.register("Parent");

        adapter.delete(relationshipType);

        verify(springDataRepository)
                .deleteById(relationshipType.id().value());
    }
}
