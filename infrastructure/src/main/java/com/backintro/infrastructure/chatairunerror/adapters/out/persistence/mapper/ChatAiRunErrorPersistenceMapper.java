package com.backintro.infrastructure.chatairunerror.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.backintro.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.backintro.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;

@Component
public class ChatAiRunErrorPersistenceMapper {

    public ChatAiRunError toDomain(ChatAiRunErrorJpaEntity entity) {
        return ChatAiRunError.restore(
                new ChatAiRunErrorId(entity.getId()),
                new ChatAiRunId(entity.getAiRunId()),
                entity.getErrorMessage(),
                entity.getErrorCode(),
                entity.getProviderErrorId()
        );
    }

    public ChatAiRunErrorJpaEntity toNewEntity(ChatAiRunError error) {
        return new ChatAiRunErrorJpaEntity(
                error.id().value(),
                error.aiRunId().value(),
                error.errorMessage(),
                error.errorCode(),
                error.providerErrorId()
        );
    }

    public void synchronize(
            ChatAiRunError error,
            ChatAiRunErrorJpaEntity entity
    ) {
        entity.synchronize(
                error.errorMessage(),
                error.errorCode(),
                error.providerErrorId()
        );
    }
}
