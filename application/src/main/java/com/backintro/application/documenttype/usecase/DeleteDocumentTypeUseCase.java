package com.backintro.application.documenttype.usecase;

import java.time.LocalDateTime;

import com.backintro.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.backintro.domain.documenttype.event.DocumentTypeDeletedEvent;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;

public class DeleteDocumentTypeUseCase {

    private final DocumentTypeRepository documentTypeRepository;

    public DeleteDocumentTypeUseCase(
            DocumentTypeRepository documentTypeRepository
    ) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentTypeDeletedEvent execute(DocumentTypeId id) {
        var documentType = documentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new DocumentTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        documentTypeRepository.delete(documentType);

        return new DocumentTypeDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
