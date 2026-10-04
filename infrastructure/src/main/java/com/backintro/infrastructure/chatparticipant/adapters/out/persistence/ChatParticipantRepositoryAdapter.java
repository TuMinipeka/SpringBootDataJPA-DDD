package com.backintro.infrastructure.chatparticipant.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.chatparticipant.port.repository.ChatParticipantRepository;
import com.backintro.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;
import com.backintro.infrastructure.chatparticipant.adapters.out.persistence.mapper.ChatParticipantPersistenceMapper;
import com.backintro.infrastructure.chatparticipant.adapters.out.persistence.repository.ChatParticipantJpaRepository;

@Repository
@Transactional
public class ChatParticipantRepositoryAdapter
        implements ChatParticipantRepository {

    private final ChatParticipantJpaRepository repository;
    private final ChatParticipantPersistenceMapper mapper;

    public ChatParticipantRepositoryAdapter(
            ChatParticipantJpaRepository repository,
            ChatParticipantPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatParticipant save(ChatParticipant chatParticipant) {
        ChatParticipantJpaEntity entity = repository
                .findById(chatParticipant.id().value())
                .map(existing -> {
                    mapper.synchronize(chatParticipant, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(chatParticipant));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatParticipant> findById(ChatParticipantId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatParticipant> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatParticipant chatParticipant) {
        repository.deleteById(chatParticipant.id().value());
    }
}
