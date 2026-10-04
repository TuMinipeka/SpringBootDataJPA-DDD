package com.backintro.application.phonecontact.usecase;

import com.backintro.application.contact.exception.ContactNotFoundApplicationException;
import com.backintro.application.phonecontact.command.RegisterPhoneContactCommand;
import com.backintro.application.phonecontact.dto.PhoneContactResponse;
import com.backintro.domain.contact.port.repository.ContactRepository;
import com.backintro.domain.phonecontact.model.aggregate.PhoneContact;
import com.backintro.domain.phonecontact.port.repository.PhoneContactRepository;

public class RegisterPhoneContactUseCase {

    private final PhoneContactRepository phoneContactRepository;
    private final ContactRepository contactRepository;

    public RegisterPhoneContactUseCase(
            PhoneContactRepository phoneContactRepository,
            ContactRepository contactRepository
    ) {
        this.phoneContactRepository = phoneContactRepository;
        this.contactRepository = contactRepository;
    }

    public PhoneContactResponse execute(RegisterPhoneContactCommand command) {
        contactRepository.findById(command.contactId())
                .orElseThrow(() ->
                        new ContactNotFoundApplicationException(
                                command.contactId().value().toString()
                        )
                );

        PhoneContact phoneContact = PhoneContact.register(
                command.contactId(),
                command.phone(),
                command.notes()
        );

        PhoneContact saved = phoneContactRepository.save(phoneContact);

        return new PhoneContactResponse(
                saved.id().value(),
                saved.phone(),
                saved.notes()
        );
    }
}
