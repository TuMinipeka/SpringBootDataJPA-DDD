package com.backintro.infrastructure.chatescalation.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatescalation.model.aggregate.ChatEscalation;
import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.backintro.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;
import com.backintro.infrastructure.chatescalation.adapters.out.persistence.mapper.ChatEscalationPersistenceMapper;
import com.backintro.infrastructure.chatescalation.adapters.out.persistence.repository.ChatEscalationJpaRepository;

@Repository
@Transactional
public class ChatEscalationRepositoryAdapter
        implements ChatEscalationRepository {

    private final ChatEscalationJpaRepository repository;
    private final ChatEscalationPersistenceMapper mapper;

    public ChatEscalationRepositoryAdapter(
            ChatEscalationJpaRepository repository,
            ChatEscalationPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalation save(ChatEscalation escalation) {
        ChatEscalationJpaEntity entity = repository
                .findById(escalation.id().value())
                .map(existing -> {
                    mapper.synchronize(escalation, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(escalation));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatEscalation> findById(ChatEscalationId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatEscalation> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatEscalation escalation) {
        repository.deleteById(escalation.id().value());
    }
}
