package com.backintro.application.emailcontact.usecase;

import com.backintro.application.contact.exception.ContactNotFoundApplicationException;
import com.backintro.application.emailcontact.command.RegisterEmailContactCommand;
import com.backintro.application.emailcontact.dto.EmailContactResponse;
import com.backintro.domain.contact.port.repository.ContactRepository;
import com.backintro.domain.emailcontact.model.aggregate.EmailContact;
import com.backintro.domain.emailcontact.port.repository.EmailContactRepository;

public class RegisterEmailContactUseCase {

    private final EmailContactRepository emailContactRepository;
    private final ContactRepository contactRepository;

    public RegisterEmailContactUseCase(
            EmailContactRepository emailContactRepository,
            ContactRepository contactRepository
    ) {
        this.emailContactRepository = emailContactRepository;
        this.contactRepository = contactRepository;
    }

    public EmailContactResponse execute(RegisterEmailContactCommand command) {
        contactRepository.findById(command.contactId())
                .orElseThrow(() ->
                        new ContactNotFoundApplicationException(
                                command.contactId().value().toString()
                        )
                );

        EmailContact emailContact = EmailContact.register(
                command.contactId(),
                command.email(),
                command.notes()
        );

        EmailContact saved = emailContactRepository.save(emailContact);

        return new EmailContactResponse(
                saved.id().value(),
                saved.email(),
                saved.notes()
        );
    }
}
