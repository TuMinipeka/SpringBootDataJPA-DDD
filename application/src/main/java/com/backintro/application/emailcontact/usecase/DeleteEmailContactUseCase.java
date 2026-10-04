package com.backintro.application.emailcontact.usecase;

import java.time.LocalDateTime;

import com.backintro.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import com.backintro.domain.emailcontact.event.EmailContactDeletedEvent;
import com.backintro.domain.emailcontact.model.valueobject.EmailContactId;
import com.backintro.domain.emailcontact.port.repository.EmailContactRepository;

public class DeleteEmailContactUseCase {

    private final EmailContactRepository emailContactRepository;

    public DeleteEmailContactUseCase(
            EmailContactRepository emailContactRepository
    ) {
        this.emailContactRepository = emailContactRepository;
    }

    public EmailContactDeletedEvent execute(EmailContactId id) {
        var emailContact = emailContactRepository.findById(id)
                .orElseThrow(() ->
                        new EmailContactNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        emailContactRepository.delete(emailContact);

        return new EmailContactDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
