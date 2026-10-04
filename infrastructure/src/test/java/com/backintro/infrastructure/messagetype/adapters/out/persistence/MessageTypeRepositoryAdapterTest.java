package com.backintro.infrastructure.messagetype.adapters.out.persistence;

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

import com.backintro.domain.messagetype.model.aggregate.MessageType;
import com.backintro.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;
import com.backintro.infrastructure.messagetype.adapters.out.persistence.mapper.MessageTypePersistenceMapper;
import com.backintro.infrastructure.messagetype.adapters.out.persistence.repository.MessageTypeJpaRepository;

@ExtendWith(MockitoExtension.class)
class MessageTypeRepositoryAdapterTest {

    @Mock
    private MessageTypeJpaRepository springDataRepository;

    private MessageTypeRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new MessageTypeRepositoryAdapter(
                springDataRepository,
                new MessageTypePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        MessageType messageType =
                MessageType.register("Text");
        when(springDataRepository.findById(messageType.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(MessageTypeJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MessageType saved = adapter.save(messageType);

        assertThat(saved.id()).isEqualTo(messageType.id());
        assertThat(saved.nameType()).isEqualTo("Text");
        verify(springDataRepository).save(any(MessageTypeJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        MessageType messageType =
                MessageType.register("Text");

        adapter.delete(messageType);

        verify(springDataRepository)
                .deleteById(messageType.id().value());
    }
}
