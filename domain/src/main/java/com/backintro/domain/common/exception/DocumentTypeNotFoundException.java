package com.backintro.domain.common.exception;

import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;

public class DocumentTypeNotFoundException extends RuntimeException {

    public DocumentTypeNotFoundException(DocumentTypeId id) {
        super("Document type not found with id: " + id.value());
    }
}
