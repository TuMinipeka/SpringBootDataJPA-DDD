package com.backintro.infrastructure.documenttype.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.documenttype.model.aggregate.DocumentType;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;
import com.backintro.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;
import com.backintro.infrastructure.documenttype.adapters.out.persistence.mapper.DocumentTypePersistenceMapper;
import com.backintro.infrastructure.documenttype.adapters.out.persistence.repository.DocumentTypeJpaRepository;

@Repository
@Transactional
public class DocumentTypeRepositoryAdapter implements DocumentTypeRepository {

    private final DocumentTypeJpaRepository repository;
    private final DocumentTypePersistenceMapper mapper;

    public DocumentTypeRepositoryAdapter(
            DocumentTypeJpaRepository repository,
            DocumentTypePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DocumentType save(DocumentType documentType) {
        DocumentTypeJpaEntity entity =
                repository.findById(documentType.id().value())
                        .map(existing -> {
                            mapper.synchronize(documentType, existing);
                            return existing;
                        })
                        .orElseGet(() -> mapper.toNewEntity(documentType));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentType> findById(DocumentTypeId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentType> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return repository.existsByCodeIgnoreCase(code);
    }

    @Override
    public void delete(DocumentType documentType) {
        repository.deleteById(documentType.id().value());
    }
}
