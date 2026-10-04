package com.backintro.infrastructure.chatparticipant.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;

class ChatParticipantPersistenceMapperTest {

    private final ChatParticipantPersistenceMapper mapper =
            new ChatParticipantPersistenceMapper();

    @Test
    void mapsChatParticipantInBothDirectionsWithoutCreatingDomainEvents() {
        ChatParticipant original = ChatParticipant.register(
                ChatConversationId.generate(),
                SenderTypeId.generate(),
                PatientId.generate(),
                null
        );

        ChatParticipant restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.conversationId())
                .isEqualTo(original.conversationId());
        assertThat(restored.participantTypeId())
                .isEqualTo(original.participantTypeId());
        assertThat(restored.patientId()).isEqualTo(original.patientId());
        assertThat(restored.professionalId()).isNull();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
