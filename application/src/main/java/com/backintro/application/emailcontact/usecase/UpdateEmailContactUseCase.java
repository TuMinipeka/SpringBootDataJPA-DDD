package com.backintro.application.emailcontact.usecase;

import com.backintro.application.emailcontact.command.UpdateEmailContactCommand;
import com.backintro.application.emailcontact.dto.EmailContactResponse;
import com.backintro.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import com.backintro.domain.emailcontact.port.repository.EmailContactRepository;

public class UpdateEmailContactUseCase {

    private final EmailContactRepository emailContactRepository;

    public UpdateEmailContactUseCase(
            EmailContactRepository emailContactRepository
    ) {
        this.emailContactRepository = emailContactRepository;
    }

    public EmailContactResponse execute(UpdateEmailContactCommand command) {
        var emailContact = emailContactRepository.findById(command.id())
                .orElseThrow(() ->
                        new EmailContactNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        emailContact.update(command.email(), command.notes());

        var updated = emailContactRepository.save(emailContact);

        return new EmailContactResponse(
                updated.id().value(),
                updated.email(),
                updated.notes()
        );
    }
}
