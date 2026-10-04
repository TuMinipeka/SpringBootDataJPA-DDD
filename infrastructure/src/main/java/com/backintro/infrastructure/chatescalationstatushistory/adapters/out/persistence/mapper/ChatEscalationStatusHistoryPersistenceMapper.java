package com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;

@Component
public class ChatEscalationStatusHistoryPersistenceMapper {

    public ChatEscalationStatusHistory toDomain(
            ChatEscalationStatusHistoryJpaEntity entity
    ) {
        return ChatEscalationStatusHistory.restore(
                new ChatEscalationStatusHistoryId(entity.getId()),
                new ChatEscalationId(entity.getEscalationId()),
                new EscalationStatusId(entity.getEscalationStatusId())
        );
    }

    public ChatEscalationStatusHistoryJpaEntity toNewEntity(
            ChatEscalationStatusHistory history
    ) {
        return new ChatEscalationStatusHistoryJpaEntity(
                history.id().value(),
                history.escalationId().value(),
                history.escalationStatusId().value()
        );
    }

    public void synchronize(
            ChatEscalationStatusHistory history,
            ChatEscalationStatusHistoryJpaEntity entity
    ) {
        entity.synchronize(history.escalationStatusId().value());
    }
}
