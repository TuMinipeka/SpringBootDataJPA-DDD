package com.backintro.application.documenttype.usecase;

import java.util.List;

import com.backintro.application.documenttype.dto.DocumentTypeResponse;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;

public class ListDocumentTypeUseCase {

    private final DocumentTypeRepository documentTypeRepository;

    public ListDocumentTypeUseCase(
            DocumentTypeRepository documentTypeRepository
    ) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public List<DocumentTypeResponse> execute() {
        return documentTypeRepository.findAll()
                .stream()
                .map(documentType ->
                        new DocumentTypeResponse(
                                documentType.id().value(),
                                documentType.name(),
                                documentType.code()
                        )
                )
                .toList();
    }
}
