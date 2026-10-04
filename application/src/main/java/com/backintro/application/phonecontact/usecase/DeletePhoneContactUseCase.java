package com.backintro.application.phonecontact.usecase;

import java.time.LocalDateTime;

import com.backintro.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import com.backintro.domain.phonecontact.event.PhoneContactDeletedEvent;
import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;
import com.backintro.domain.phonecontact.port.repository.PhoneContactRepository;

public class DeletePhoneContactUseCase {

    private final PhoneContactRepository phoneContactRepository;

    public DeletePhoneContactUseCase(
            PhoneContactRepository phoneContactRepository
    ) {
        this.phoneContactRepository = phoneContactRepository;
    }

    public PhoneContactDeletedEvent execute(PhoneContactId id) {
        var phoneContact = phoneContactRepository.findById(id)
                .orElseThrow(() ->
                        new PhoneContactNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        phoneContactRepository.delete(phoneContact);

        return new PhoneContactDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
