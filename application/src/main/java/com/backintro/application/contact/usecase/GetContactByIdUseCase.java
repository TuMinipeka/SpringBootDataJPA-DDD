package com.backintro.application.contact.usecase;

import com.backintro.application.contact.dto.ContactResponse;
import com.backintro.application.contact.exception.ContactNotFoundApplicationException;
import com.backintro.domain.contact.model.aggregate.Contact;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.contact.port.repository.ContactRepository;

public class GetContactByIdUseCase {

    private final ContactRepository contactRepository;

    public GetContactByIdUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactResponse execute(ContactId id) {
        var contact = contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(contact);
    }

    private ContactResponse toResponse(Contact contact) {
        return new ContactResponse(
                contact.id().value(),
                contact.fullName(),
                contact.email(),
                contact.notes(),
                contact.cityId() == null ? null : contact.cityId().value()
        );
    }
}
