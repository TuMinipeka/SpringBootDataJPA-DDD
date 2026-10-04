package com.backintro.application.documenttype.usecase;

import com.backintro.application.documenttype.command.UpdateDocumentTypeCommand;
import com.backintro.application.documenttype.dto.DocumentTypeResponse;
import com.backintro.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;

public class UpdateDocumentTypeUseCase {

    private final DocumentTypeRepository documentTypeRepository;

    public UpdateDocumentTypeUseCase(
            DocumentTypeRepository documentTypeRepository
    ) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentTypeResponse execute(UpdateDocumentTypeCommand command) {
        var documentType = documentTypeRepository.findById(command.id())
                .orElseThrow(() ->
                        new DocumentTypeNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        documentType.update(command.name(), command.code());

        var updated = documentTypeRepository.save(documentType);

        return new DocumentTypeResponse(
                updated.id().value(),
                updated.name(),
                updated.code()
        );
    }
}
