package com.backintro.infrastructure.chatparticipant.adapters.out.persistence;

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
import com.backintro.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;
import com.backintro.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;
import com.backintro.infrastructure.chatparticipant.adapters.out.persistence.mapper.ChatParticipantPersistenceMapper;
import com.backintro.infrastructure.chatparticipant.adapters.out.persistence.repository.ChatParticipantJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatParticipantRepositoryAdapterTest {

    @Mock
    private ChatParticipantJpaRepository springDataRepository;

    private ChatParticipantRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatParticipantRepositoryAdapter(
                springDataRepository,
                new ChatParticipantPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatParticipant chatParticipant = newChatParticipant();
        when(springDataRepository.findById(chatParticipant.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ChatParticipantJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ChatParticipant saved = adapter.save(chatParticipant);

        assertThat(saved.id()).isEqualTo(chatParticipant.id());
        assertThat(saved.conversationId())
                .isEqualTo(chatParticipant.conversationId());
        assertThat(saved.participantTypeId())
                .isEqualTo(chatParticipant.participantTypeId());
        assertThat(saved.patientId()).isEqualTo(chatParticipant.patientId());
        assertThat(saved.professionalId()).isNull();
        verify(springDataRepository)
                .save(any(ChatParticipantJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatParticipant chatParticipant = newChatParticipant();

        adapter.delete(chatParticipant);

        verify(springDataRepository)
                .deleteById(chatParticipant.id().value());
    }

    private ChatParticipant newChatParticipant() {
        return ChatParticipant.register(
                ChatConversationId.generate(),
                SenderTypeId.generate(),
                PatientId.generate(),
                null
        );
    }
}
