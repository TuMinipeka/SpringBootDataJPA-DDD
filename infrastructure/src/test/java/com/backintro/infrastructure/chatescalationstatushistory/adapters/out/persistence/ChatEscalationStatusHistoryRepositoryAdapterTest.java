package com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence;

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

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;
import com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.mapper.ChatEscalationStatusHistoryPersistenceMapper;
import com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.repository.ChatEscalationStatusHistoryJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatEscalationStatusHistoryRepositoryAdapterTest {

    @Mock
    private ChatEscalationStatusHistoryJpaRepository springDataRepository;

    private ChatEscalationStatusHistoryRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatEscalationStatusHistoryRepositoryAdapter(
                springDataRepository,
                new ChatEscalationStatusHistoryPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatEscalationStatusHistory history = newHistory();
        when(springDataRepository.findById(history.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(
                any(ChatEscalationStatusHistoryJpaEntity.class)
        )).thenAnswer(invocation -> invocation.getArgument(0));

        ChatEscalationStatusHistory saved = adapter.save(history);

        assertThat(saved.id()).isEqualTo(history.id());
        assertThat(saved.escalationId()).isEqualTo(history.escalationId());
        assertThat(saved.escalationStatusId())
                .isEqualTo(history.escalationStatusId());
        verify(springDataRepository)
                .save(any(ChatEscalationStatusHistoryJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatEscalationStatusHistory history = newHistory();

        adapter.delete(history);

        verify(springDataRepository).deleteById(history.id().value());
    }

    private ChatEscalationStatusHistory newHistory() {
        return ChatEscalationStatusHistory.register(
                ChatEscalationId.generate(),
                EscalationStatusId.generate()
        );
    }
}
