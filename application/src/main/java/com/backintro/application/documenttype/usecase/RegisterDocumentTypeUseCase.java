package com.backintro.application.documenttype.usecase;

import com.backintro.application.documenttype.command.RegisterDocumentTypeCommand;
import com.backintro.application.documenttype.dto.DocumentTypeResponse;
import com.backintro.domain.documenttype.model.aggregate.DocumentType;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;

public class RegisterDocumentTypeUseCase {

    private final DocumentTypeRepository documentTypeRepository;

    public RegisterDocumentTypeUseCase(
            DocumentTypeRepository documentTypeRepository
    ) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentTypeResponse execute(RegisterDocumentTypeCommand command) {
        DocumentType documentType = DocumentType.register(
                command.name(),
                command.code()
        );

        DocumentType saved = documentTypeRepository.save(documentType);

        return new DocumentTypeResponse(
                saved.id().value(),
                saved.name(),
                saved.code()
        );
    }
}
