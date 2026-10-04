package com.backintro.application.phonecontact.usecase;

import com.backintro.application.phonecontact.dto.PhoneContactResponse;
import com.backintro.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;
import com.backintro.domain.phonecontact.port.repository.PhoneContactRepository;

public class GetPhoneContactByIdUseCase {

    private final PhoneContactRepository phoneContactRepository;

    public GetPhoneContactByIdUseCase(
            PhoneContactRepository phoneContactRepository
    ) {
        this.phoneContactRepository = phoneContactRepository;
    }

    public PhoneContactResponse execute(PhoneContactId id) {
        var phoneContact = phoneContactRepository.findById(id)
                .orElseThrow(() ->
                        new PhoneContactNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new PhoneContactResponse(
                phoneContact.id().value(),
                phoneContact.phone(),
                phoneContact.notes()
        );
    }
}
