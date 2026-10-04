package com.backintro.application.contact.usecase;

import com.backintro.application.contact.command.RegisterContactCommand;
import com.backintro.application.contact.dto.ContactResponse;
import com.backintro.domain.contact.model.aggregate.Contact;
import com.backintro.domain.contact.port.repository.ContactRepository;

public class RegisterContactUseCase {

    private final ContactRepository contactRepository;

    public RegisterContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactResponse execute(RegisterContactCommand command) {
        Contact contact = Contact.register(
                command.fullName(),
                command.email(),
                command.notes(),
                command.cityId()
        );
        Contact saved = contactRepository.save(contact);

        return toResponse(saved);
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
