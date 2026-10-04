package com.backintro.infrastructure.messagetype.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.messagetype.model.aggregate.MessageType;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;
import com.backintro.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;
import com.backintro.infrastructure.messagetype.adapters.out.persistence.mapper.MessageTypePersistenceMapper;
import com.backintro.infrastructure.messagetype.adapters.out.persistence.repository.MessageTypeJpaRepository;

@Repository
@Transactional
public class MessageTypeRepositoryAdapter
        implements MessageTypeRepository {

    private final MessageTypeJpaRepository repository;
    private final MessageTypePersistenceMapper mapper;

    public MessageTypeRepositoryAdapter(
            MessageTypeJpaRepository repository,
            MessageTypePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MessageType save(MessageType messageType) {
        MessageTypeJpaEntity entity = repository
                .findById(messageType.id().value())
                .map(existing -> {
                    mapper.synchronize(messageType, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(messageType));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MessageType> findById(MessageTypeId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageType> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNameType(String nameType) {
        return repository.existsByNameTypeIgnoreCase(nameType);
    }

    @Override
    public void delete(MessageType messageType) {
        repository.deleteById(messageType.id().value());
    }
}
