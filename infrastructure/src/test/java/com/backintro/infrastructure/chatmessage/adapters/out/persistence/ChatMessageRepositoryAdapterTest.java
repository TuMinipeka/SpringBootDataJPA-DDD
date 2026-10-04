package com.backintro.infrastructure.chatmessage.adapters.out.persistence;

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

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatmessage.model.aggregate.ChatMessage;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;
import com.backintro.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;
import com.backintro.infrastructure.chatmessage.adapters.out.persistence.mapper.ChatMessagePersistenceMapper;
import com.backintro.infrastructure.chatmessage.adapters.out.persistence.repository.ChatMessageJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatMessageRepositoryAdapterTest {

    @Mock
    private ChatMessageJpaRepository springDataRepository;

    private ChatMessageRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatMessageRepositoryAdapter(
                springDataRepository,
                new ChatMessagePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatMessage chatMessage = newChatMessage();
        when(springDataRepository.findById(chatMessage.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ChatMessageJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ChatMessage saved = adapter.save(chatMessage);

        assertThat(saved.id()).isEqualTo(chatMessage.id());
        assertThat(saved.conversationId())
                .isEqualTo(chatMessage.conversationId());
        assertThat(saved.messageTypeId())
                .isEqualTo(chatMessage.messageTypeId());
        assertThat(saved.participantId())
                .isEqualTo(chatMessage.participantId());
        assertThat(saved.content()).isEqualTo("{\"text\":\"Hello\"}");
        assertThat(saved.metadata()).isEqualTo("{\"source\":\"web\"}");
        verify(springDataRepository).save(any(ChatMessageJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatMessage chatMessage = newChatMessage();

        adapter.delete(chatMessage);

        verify(springDataRepository)
                .deleteById(chatMessage.id().value());
    }

    private ChatMessage newChatMessage() {
        return ChatMessage.register(
                ChatConversationId.generate(),
                MessageTypeId.generate(),
                ChatParticipantId.generate(),
                "{\"text\":\"Hello\"}",
                "{\"source\":\"web\"}"
        );
    }
}
