package com.backintro.infrastructure.chatescalation.adapters.out.persistence;

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

import com.backintro.domain.chatescalation.model.aggregate.ChatEscalation;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;
import com.backintro.infrastructure.chatescalation.adapters.out.persistence.mapper.ChatEscalationPersistenceMapper;
import com.backintro.infrastructure.chatescalation.adapters.out.persistence.repository.ChatEscalationJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatEscalationRepositoryAdapterTest {

    @Mock
    private ChatEscalationJpaRepository springDataRepository;

    private ChatEscalationRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatEscalationRepositoryAdapter(
                springDataRepository,
                new ChatEscalationPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatEscalation escalation = newEscalation();
        when(springDataRepository.findById(escalation.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ChatEscalationJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ChatEscalation saved = adapter.save(escalation);

        assertThat(saved.id()).isEqualTo(escalation.id());
        assertThat(saved.conversationId())
                .isEqualTo(escalation.conversationId());
        assertThat(saved.statusId()).isEqualTo(escalation.statusId());
        assertThat(saved.fromAi()).isFalse();
        assertThat(saved.reason()).isEqualTo("Human assistance is required");
        verify(springDataRepository).save(any(ChatEscalationJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatEscalation escalation = newEscalation();

        adapter.delete(escalation);

        verify(springDataRepository).deleteById(escalation.id().value());
    }

    private ChatEscalation newEscalation() {
        return ChatEscalation.register(
                ChatConversationId.generate(),
                EscalationStatusId.generate(),
                "Human assistance is required"
        );
    }
}
