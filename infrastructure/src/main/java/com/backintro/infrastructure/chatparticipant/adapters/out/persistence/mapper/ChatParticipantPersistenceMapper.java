package com.backintro.infrastructure.chatparticipant.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;
import com.backintro.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;

@Component
public class ChatParticipantPersistenceMapper {

    public ChatParticipant toDomain(ChatParticipantJpaEntity entity) {
        return ChatParticipant.restore(
                new ChatParticipantId(entity.getId()),
                new ChatConversationId(entity.getConversationId()),
                new SenderTypeId(entity.getParticipantTypeId()),
                entity.getPatientId() == null
                        ? null
                        : new PatientId(entity.getPatientId()),
                entity.getProfessionalId() == null
                        ? null
                        : new ProfessionalId(entity.getProfessionalId())
        );
    }

    public ChatParticipantJpaEntity toNewEntity(
            ChatParticipant chatParticipant
    ) {
        return new ChatParticipantJpaEntity(
                chatParticipant.id().value(),
                chatParticipant.conversationId().value(),
                chatParticipant.participantTypeId().value(),
                chatParticipant.patientId() == null
                        ? null
                        : chatParticipant.patientId().value(),
                chatParticipant.professionalId() == null
                        ? null
                        : chatParticipant.professionalId().value()
        );
    }

    public void synchronize(
            ChatParticipant chatParticipant,
            ChatParticipantJpaEntity entity
    ) {
        entity.synchronize(
                chatParticipant.participantTypeId().value(),
                chatParticipant.patientId() == null
                        ? null
                        : chatParticipant.patientId().value(),
                chatParticipant.professionalId() == null
                        ? null
                        : chatParticipant.professionalId().value()
        );
    }
}
