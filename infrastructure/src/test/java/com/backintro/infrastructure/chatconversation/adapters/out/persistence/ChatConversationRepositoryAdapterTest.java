package com.backintro.infrastructure.chatconversation.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.chatconversation.model.aggregate.ChatConversation;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.priority.model.valueobject.PriorityId;
import com.backintro.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;
import com.backintro.infrastructure.chatconversation.adapters.out.persistence.mapper.ChatConversationPersistenceMapper;
import com.backintro.infrastructure.chatconversation.adapters.out.persistence.repository.ChatConversationJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatConversationRepositoryAdapterTest {

    @Mock
    private ChatConversationJpaRepository springDataRepository;

    private ChatConversationRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatConversationRepositoryAdapter(
                springDataRepository,
                new ChatConversationPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatConversation chatConversation = newChatConversation();
        when(springDataRepository.findById(chatConversation.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ChatConversationJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ChatConversation saved = adapter.save(chatConversation);

        assertThat(saved.id()).isEqualTo(chatConversation.id());
        assertThat(saved.conversationStatusId())
                .isEqualTo(chatConversation.conversationStatusId());
        assertThat(saved.priorityId())
                .isEqualTo(chatConversation.priorityId());
        assertThat(saved.lastMessageAt())
                .isEqualTo(chatConversation.lastMessageAt());
        assertThat(saved.closed()).isFalse();
        assertThat(saved.closedAt()).isNull();
        assertThat(saved.closedBy()).isNull();
        verify(springDataRepository)
                .save(any(ChatConversationJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatConversation chatConversation = newChatConversation();

        adapter.delete(chatConversation);

        verify(springDataRepository)
                .deleteById(chatConversation.id().value());
    }

    private ChatConversation newChatConversation() {
        return ChatConversation.register(
                ConversationStatusId.generate(),
                PriorityId.generate(),
                LocalDateTime.of(2026, 10, 4, 10, 30)
        );
    }
}
