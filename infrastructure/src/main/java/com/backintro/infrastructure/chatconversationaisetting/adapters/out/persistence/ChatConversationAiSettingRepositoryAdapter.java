package com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.backintro.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;
import com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;
import com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.mapper.ChatConversationAiSettingPersistenceMapper;
import com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.repository.ChatConversationAiSettingJpaRepository;

@Repository
@Transactional
public class ChatConversationAiSettingRepositoryAdapter
        implements ChatConversationAiSettingRepository {

    private final ChatConversationAiSettingJpaRepository repository;
    private final ChatConversationAiSettingPersistenceMapper mapper;

    public ChatConversationAiSettingRepositoryAdapter(
            ChatConversationAiSettingJpaRepository repository,
            ChatConversationAiSettingPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatConversationAiSetting save(
            ChatConversationAiSetting setting
    ) {
        ChatConversationAiSettingJpaEntity entity = repository
                .findById(setting.id().value())
                .map(existing -> {
                    mapper.synchronize(setting, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(setting));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatConversationAiSetting> findById(
            ChatConversationAiSettingId id
    ) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatConversationAiSetting> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByConversationId(
            ChatConversationId conversationId
    ) {
        return repository.existsByConversationId(conversationId.value());
    }

    @Override
    public void delete(ChatConversationAiSetting setting) {
        repository.deleteById(setting.id().value());
    }
}
