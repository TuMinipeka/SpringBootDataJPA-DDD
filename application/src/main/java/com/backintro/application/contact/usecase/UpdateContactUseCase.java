package com.backintro.application.contact.usecase;

import com.backintro.application.contact.command.UpdateContactCommand;
import com.backintro.application.contact.dto.ContactResponse;
import com.backintro.application.contact.exception.ContactNotFoundApplicationException;
import com.backintro.domain.contact.model.aggregate.Contact;
import com.backintro.domain.contact.port.repository.ContactRepository;

public class UpdateContactUseCase {

    private final ContactRepository contactRepository;

    public UpdateContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactResponse execute(UpdateContactCommand command) {
        var contact = contactRepository.findById(command.id())
                .orElseThrow(() ->
                        new ContactNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        contact.update(
                command.fullName(),
                command.email(),
                command.notes(),
                command.cityId()
        );

        return toResponse(contactRepository.save(contact));
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
