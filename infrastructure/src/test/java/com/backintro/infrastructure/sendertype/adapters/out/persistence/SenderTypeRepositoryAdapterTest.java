package com.backintro.infrastructure.sendertype.adapters.out.persistence;

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

import com.backintro.domain.sendertype.model.aggregate.SenderType;
import com.backintro.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;
import com.backintro.infrastructure.sendertype.adapters.out.persistence.mapper.SenderTypePersistenceMapper;
import com.backintro.infrastructure.sendertype.adapters.out.persistence.repository.SenderTypeJpaRepository;

@ExtendWith(MockitoExtension.class)
class SenderTypeRepositoryAdapterTest {

    @Mock
    private SenderTypeJpaRepository springDataRepository;

    private SenderTypeRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new SenderTypeRepositoryAdapter(
                springDataRepository,
                new SenderTypePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        SenderType senderType =
                SenderType.register("Professional");
        when(springDataRepository.findById(senderType.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(SenderTypeJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SenderType saved = adapter.save(senderType);

        assertThat(saved.id()).isEqualTo(senderType.id());
        assertThat(saved.nameType()).isEqualTo("Professional");
        verify(springDataRepository).save(any(SenderTypeJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        SenderType senderType =
                SenderType.register("Professional");

        adapter.delete(senderType);

        verify(springDataRepository)
                .deleteById(senderType.id().value());
    }
}
