package com.backintro.infrastructure.chatconversation.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatconversation.model.aggregate.ChatConversation;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;
import com.backintro.infrastructure.chatconversation.adapters.out.persistence.mapper.ChatConversationPersistenceMapper;
import com.backintro.infrastructure.chatconversation.adapters.out.persistence.repository.ChatConversationJpaRepository;

@Repository
@Transactional
public class ChatConversationRepositoryAdapter
        implements ChatConversationRepository {

    private final ChatConversationJpaRepository repository;
    private final ChatConversationPersistenceMapper mapper;

    public ChatConversationRepositoryAdapter(
            ChatConversationJpaRepository repository,
            ChatConversationPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatConversation save(ChatConversation chatConversation) {
        ChatConversationJpaEntity entity = repository
                .findById(chatConversation.id().value())
                .map(existing -> {
                    mapper.synchronize(chatConversation, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(chatConversation));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatConversation> findById(ChatConversationId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatConversation> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ChatConversation chatConversation) {
        repository.deleteById(chatConversation.id().value());
    }
}
