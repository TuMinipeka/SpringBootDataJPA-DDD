package com.backintro.application.contact.usecase;

import java.time.LocalDateTime;

import com.backintro.application.contact.exception.ContactNotFoundApplicationException;
import com.backintro.domain.contact.event.ContactDeletedEvent;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.contact.port.repository.ContactRepository;

public class DeleteContactUseCase {

    private final ContactRepository contactRepository;

    public DeleteContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDeletedEvent execute(ContactId id) {
        var contact = contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        contactRepository.delete(contact);

        return new ContactDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
