package com.backintro.domain.documenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.documenttype.event.DocumentTypeRegisteredEvent;
import com.backintro.domain.documenttype.event.DocumentTypeUpdatedEvent;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;

public class DocumentType extends AggregateRoot {

    private final DocumentTypeId id;
    private String name;
    private String code;
    private boolean active;

    private DocumentType(
            DocumentTypeId id,
            String name,
            String code,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
    }

    public static DocumentType register(
            String name,
            String code
    ) {
        DocumentTypeId id = DocumentTypeId.generate();
        DocumentType documentType = new DocumentType(
                id,
                name,
                code,
                true
        );

        documentType.recordEvent(
                new DocumentTypeRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return documentType;
    }

    public static DocumentType restore(
            DocumentTypeId id,
            String name,
            String code,
            boolean active
    ) {
        return new DocumentType(id, name, code, active);
    }

    public void update(
            String name,
            String code
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");

        recordEvent(
                new DocumentTypeUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        LocalDateTime.now()
                )
        );
    }

    public DocumentTypeId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String code() {
        return code;
    }

    public boolean active() {
        return active;
    }
}
