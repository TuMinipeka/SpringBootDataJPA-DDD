package com.backintro.infrastructure.documenttype.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.documenttype.model.aggregate.DocumentType;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;

@Component
public class DocumentTypePersistenceMapper {

    public DocumentType toDomain(DocumentTypeJpaEntity entity) {
        return DocumentType.restore(
                new DocumentTypeId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive()
        );
    }

    public DocumentTypeJpaEntity toNewEntity(DocumentType documentType) {
        return new DocumentTypeJpaEntity(
                documentType.id().value(),
                documentType.name(),
                documentType.code(),
                documentType.active()
        );
    }

    public void synchronize(
            DocumentType documentType,
            DocumentTypeJpaEntity entity
    ) {
        entity.synchronize(
                documentType.name(),
                documentType.code(),
                documentType.active()
        );
    }
}
