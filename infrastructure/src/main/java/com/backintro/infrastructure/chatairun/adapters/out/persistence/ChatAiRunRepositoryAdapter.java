package com.backintro.infrastructure.chatairun.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatairun.model.aggregate.ChatAiRun;
import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;
import com.backintro.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;
import com.backintro.infrastructure.chatairun.adapters.out.persistence.mapper.ChatAiRunPersistenceMapper;
import com.backintro.infrastructure.chatairun.adapters.out.persistence.repository.ChatAiRunJpaRepository;

@Repository
@Transactional
public class ChatAiRunRepositoryAdapter implements ChatAiRunRepository {

    private final ChatAiRunJpaRepository repository;
    private final ChatAiRunPersistenceMapper mapper;

    public ChatAiRunRepositoryAdapter(
            ChatAiRunJpaRepository repository,
            ChatAiRunPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRun save(ChatAiRun chatAiRun) {
        ChatAiRunJpaEntity entity = repository
                .findById(chatAiRun.id().value())
                .map(existing -> {
                    mapper.synchronize(chatAiRun, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(chatAiRun));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatAiRun> findById(ChatAiRunId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatAiRun> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatAiRun chatAiRun) {
        repository.deleteById(chatAiRun.id().value());
    }
}
