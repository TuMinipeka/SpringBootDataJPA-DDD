package com.backintro.infrastructure.conversationstatus.adapters.out.persistence;

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

import com.backintro.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.backintro.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;
import com.backintro.infrastructure.conversationstatus.adapters.out.persistence.mapper.ConversationStatusPersistenceMapper;
import com.backintro.infrastructure.conversationstatus.adapters.out.persistence.repository.ConversationStatusJpaRepository;

@ExtendWith(MockitoExtension.class)
class ConversationStatusRepositoryAdapterTest {

    @Mock
    private ConversationStatusJpaRepository springDataRepository;

    private ConversationStatusRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ConversationStatusRepositoryAdapter(
                springDataRepository,
                new ConversationStatusPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ConversationStatus conversationStatus =
                ConversationStatus.register("Open");
        when(springDataRepository.findById(conversationStatus.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ConversationStatusJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ConversationStatus saved = adapter.save(conversationStatus);

        assertThat(saved.id()).isEqualTo(conversationStatus.id());
        assertThat(saved.nameStatus()).isEqualTo("Open");
        verify(springDataRepository).save(any(ConversationStatusJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ConversationStatus conversationStatus =
                ConversationStatus.register("Open");

        adapter.delete(conversationStatus);

        verify(springDataRepository)
                .deleteById(conversationStatus.id().value());
    }
}
