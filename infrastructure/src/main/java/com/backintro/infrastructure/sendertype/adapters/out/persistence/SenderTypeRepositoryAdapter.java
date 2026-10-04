package com.backintro.infrastructure.sendertype.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.sendertype.model.aggregate.SenderType;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;
import com.backintro.domain.sendertype.port.repository.SenderTypeRepository;
import com.backintro.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;
import com.backintro.infrastructure.sendertype.adapters.out.persistence.mapper.SenderTypePersistenceMapper;
import com.backintro.infrastructure.sendertype.adapters.out.persistence.repository.SenderTypeJpaRepository;

@Repository
@Transactional
public class SenderTypeRepositoryAdapter
        implements SenderTypeRepository {

    private final SenderTypeJpaRepository repository;
    private final SenderTypePersistenceMapper mapper;

    public SenderTypeRepositoryAdapter(
            SenderTypeJpaRepository repository,
            SenderTypePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SenderType save(SenderType senderType) {
        SenderTypeJpaEntity entity = repository
                .findById(senderType.id().value())
                .map(existing -> {
                    mapper.synchronize(senderType, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(senderType));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SenderType> findById(SenderTypeId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SenderType> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNameType(String nameType) {
        return repository.existsByNameTypeIgnoreCase(nameType);
    }

    @Override
    public void delete(SenderType senderType) {
        repository.deleteById(senderType.id().value());
    }
}
