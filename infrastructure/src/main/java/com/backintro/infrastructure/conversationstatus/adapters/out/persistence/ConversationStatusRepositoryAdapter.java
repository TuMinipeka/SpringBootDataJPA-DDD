package com.backintro.infrastructure.conversationstatus.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;
import com.backintro.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;
import com.backintro.infrastructure.conversationstatus.adapters.out.persistence.mapper.ConversationStatusPersistenceMapper;
import com.backintro.infrastructure.conversationstatus.adapters.out.persistence.repository.ConversationStatusJpaRepository;

@Repository
@Transactional
public class ConversationStatusRepositoryAdapter
        implements ConversationStatusRepository {

    private final ConversationStatusJpaRepository repository;
    private final ConversationStatusPersistenceMapper mapper;

    public ConversationStatusRepositoryAdapter(
            ConversationStatusJpaRepository repository,
            ConversationStatusPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ConversationStatus save(ConversationStatus conversationStatus) {
        ConversationStatusJpaEntity entity = repository
                .findById(conversationStatus.id().value())
                .map(existing -> {
                    mapper.synchronize(conversationStatus, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(conversationStatus));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ConversationStatus> findById(ConversationStatusId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConversationStatus> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNameStatus(String nameStatus) {
        return repository.existsByNameStatusIgnoreCase(nameStatus);
    }

    @Override
    public void delete(ConversationStatus conversationStatus) {
        repository.deleteById(conversationStatus.id().value());
    }
}
