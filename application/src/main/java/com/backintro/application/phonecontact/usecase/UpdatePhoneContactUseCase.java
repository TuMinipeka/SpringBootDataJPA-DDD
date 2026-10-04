package com.backintro.application.phonecontact.usecase;

import com.backintro.application.phonecontact.command.UpdatePhoneContactCommand;
import com.backintro.application.phonecontact.dto.PhoneContactResponse;
import com.backintro.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import com.backintro.domain.phonecontact.port.repository.PhoneContactRepository;

public class UpdatePhoneContactUseCase {

    private final PhoneContactRepository phoneContactRepository;

    public UpdatePhoneContactUseCase(
            PhoneContactRepository phoneContactRepository
    ) {
        this.phoneContactRepository = phoneContactRepository;
    }

    public PhoneContactResponse execute(UpdatePhoneContactCommand command) {
        var phoneContact = phoneContactRepository.findById(command.id())
                .orElseThrow(() ->
                        new PhoneContactNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        phoneContact.update(command.phone(), command.notes());

        var updated = phoneContactRepository.save(phoneContact);

        return new PhoneContactResponse(
                updated.id().value(),
                updated.phone(),
                updated.notes()
        );
    }
}
