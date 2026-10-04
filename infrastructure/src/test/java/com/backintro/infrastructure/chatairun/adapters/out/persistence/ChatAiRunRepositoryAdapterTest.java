package com.backintro.infrastructure.chatairun.adapters.out.persistence;

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

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.chatairun.model.aggregate.ChatAiRun;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;
import com.backintro.infrastructure.chatairun.adapters.out.persistence.mapper.ChatAiRunPersistenceMapper;
import com.backintro.infrastructure.chatairun.adapters.out.persistence.repository.ChatAiRunJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatAiRunRepositoryAdapterTest {

    @Mock
    private ChatAiRunJpaRepository springDataRepository;

    private ChatAiRunRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatAiRunRepositoryAdapter(
                springDataRepository,
                new ChatAiRunPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatAiRun chatAiRun = newChatAiRun();
        when(springDataRepository.findById(chatAiRun.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ChatAiRunJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ChatAiRun saved = adapter.save(chatAiRun);

        assertThat(saved.id()).isEqualTo(chatAiRun.id());
        assertThat(saved.conversationId())
                .isEqualTo(chatAiRun.conversationId());
        assertThat(saved.messageId()).isEqualTo(chatAiRun.messageId());
        assertThat(saved.modelId()).isEqualTo(chatAiRun.modelId());
        assertThat(saved.aiRunStatusId())
                .isEqualTo(chatAiRun.aiRunStatusId());
        verify(springDataRepository).save(any(ChatAiRunJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatAiRun chatAiRun = newChatAiRun();

        adapter.delete(chatAiRun);

        verify(springDataRepository).deleteById(chatAiRun.id().value());
    }

    private ChatAiRun newChatAiRun() {
        return ChatAiRun.register(
                ChatConversationId.generate(),
                ChatMessageId.generate(),
                AiModelId.generate(),
                AiRunStatusId.generate()
        );
    }
}
