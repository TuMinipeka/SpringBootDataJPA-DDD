package com.backintro.infrastructure.chatmessage.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatmessage.model.aggregate.ChatMessage;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;
import com.backintro.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;
import com.backintro.infrastructure.chatmessage.adapters.out.persistence.mapper.ChatMessagePersistenceMapper;
import com.backintro.infrastructure.chatmessage.adapters.out.persistence.repository.ChatMessageJpaRepository;

@Repository
@Transactional
public class ChatMessageRepositoryAdapter
        implements ChatMessageRepository {

    private final ChatMessageJpaRepository repository;
    private final ChatMessagePersistenceMapper mapper;

    public ChatMessageRepositoryAdapter(
            ChatMessageJpaRepository repository,
            ChatMessagePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        ChatMessageJpaEntity entity = repository
                .findById(chatMessage.id().value())
                .map(existing -> {
                    mapper.synchronize(chatMessage, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(chatMessage));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatMessage> findById(ChatMessageId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessage> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatMessage chatMessage) {
        repository.deleteById(chatMessage.id().value());
    }
}
