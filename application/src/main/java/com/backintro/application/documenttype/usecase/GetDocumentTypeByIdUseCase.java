package com.backintro.application.documenttype.usecase;

import com.backintro.application.documenttype.dto.DocumentTypeResponse;
import com.backintro.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;

public class GetDocumentTypeByIdUseCase {

    private final DocumentTypeRepository documentTypeRepository;

    public GetDocumentTypeByIdUseCase(
            DocumentTypeRepository documentTypeRepository
    ) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentTypeResponse execute(DocumentTypeId id) {
        var documentType = documentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new DocumentTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new DocumentTypeResponse(
                documentType.id().value(),
                documentType.name(),
                documentType.code()
        );
    }
}
