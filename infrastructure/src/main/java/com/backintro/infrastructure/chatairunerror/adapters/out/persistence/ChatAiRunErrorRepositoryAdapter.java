package com.backintro.infrastructure.chatairunerror.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.backintro.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.backintro.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;
import com.backintro.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;
import com.backintro.infrastructure.chatairunerror.adapters.out.persistence.mapper.ChatAiRunErrorPersistenceMapper;
import com.backintro.infrastructure.chatairunerror.adapters.out.persistence.repository.ChatAiRunErrorJpaRepository;

@Repository
@Transactional
public class ChatAiRunErrorRepositoryAdapter
        implements ChatAiRunErrorRepository {

    private final ChatAiRunErrorJpaRepository repository;
    private final ChatAiRunErrorPersistenceMapper mapper;

    public ChatAiRunErrorRepositoryAdapter(
            ChatAiRunErrorJpaRepository repository,
            ChatAiRunErrorPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRunError save(ChatAiRunError error) {
        ChatAiRunErrorJpaEntity entity = repository
                .findById(error.id().value())
                .map(existing -> {
                    mapper.synchronize(error, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(error));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatAiRunError> findById(ChatAiRunErrorId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatAiRunError> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatAiRunError error) {
        repository.deleteById(error.id().value());
    }
}
