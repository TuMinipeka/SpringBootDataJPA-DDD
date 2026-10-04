package com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence;

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
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;
import com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.mapper.ChatConversationAiSettingPersistenceMapper;
import com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.repository.ChatConversationAiSettingJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatConversationAiSettingRepositoryAdapterTest {

    @Mock
    private ChatConversationAiSettingJpaRepository springDataRepository;

    private ChatConversationAiSettingRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatConversationAiSettingRepositoryAdapter(
                springDataRepository,
                new ChatConversationAiSettingPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatConversationAiSetting setting = newSetting();
        when(springDataRepository.findById(setting.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(
                any(ChatConversationAiSettingJpaEntity.class)
        )).thenAnswer(invocation -> invocation.getArgument(0));

        ChatConversationAiSetting saved = adapter.save(setting);

        assertThat(saved.id()).isEqualTo(setting.id());
        assertThat(saved.conversationId())
                .isEqualTo(setting.conversationId());
        assertThat(saved.aiEnabled()).isTrue();
        assertThat(saved.defaultModelId())
                .isEqualTo(setting.defaultModelId());
        verify(springDataRepository)
                .save(any(ChatConversationAiSettingJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatConversationAiSetting setting = newSetting();

        adapter.delete(setting);

        verify(springDataRepository).deleteById(setting.id().value());
    }

    private ChatConversationAiSetting newSetting() {
        return ChatConversationAiSetting.register(
                ChatConversationId.generate(),
                AiModelId.generate()
        );
    }
}
