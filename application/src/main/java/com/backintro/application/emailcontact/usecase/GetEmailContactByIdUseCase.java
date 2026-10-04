package com.backintro.application.emailcontact.usecase;

import com.backintro.application.emailcontact.dto.EmailContactResponse;
import com.backintro.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import com.backintro.domain.emailcontact.model.valueobject.EmailContactId;
import com.backintro.domain.emailcontact.port.repository.EmailContactRepository;

public class GetEmailContactByIdUseCase {

    private final EmailContactRepository emailContactRepository;

    public GetEmailContactByIdUseCase(
            EmailContactRepository emailContactRepository
    ) {
        this.emailContactRepository = emailContactRepository;
    }

    public EmailContactResponse execute(EmailContactId id) {
        var emailContact = emailContactRepository.findById(id)
                .orElseThrow(() ->
                        new EmailContactNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new EmailContactResponse(
                emailContact.id().value(),
                emailContact.email(),
                emailContact.notes()
        );
    }
}
