package com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.backintro.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;
import com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.mapper.ChatEscalationStatusHistoryPersistenceMapper;
import com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.repository.ChatEscalationStatusHistoryJpaRepository;

@Repository
@Transactional
public class ChatEscalationStatusHistoryRepositoryAdapter
        implements ChatEscalationStatusHistoryRepository {

    private final ChatEscalationStatusHistoryJpaRepository repository;
    private final ChatEscalationStatusHistoryPersistenceMapper mapper;

    public ChatEscalationStatusHistoryRepositoryAdapter(
            ChatEscalationStatusHistoryJpaRepository repository,
            ChatEscalationStatusHistoryPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalationStatusHistory save(
            ChatEscalationStatusHistory history
    ) {
        ChatEscalationStatusHistoryJpaEntity entity = repository
                .findById(history.id().value())
                .map(existing -> {
                    mapper.synchronize(history, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(history));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatEscalationStatusHistory> findById(
            ChatEscalationStatusHistoryId id
    ) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatEscalationStatusHistory> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatEscalationStatusHistory history) {
        repository.deleteById(history.id().value());
    }
}
