package com.backintro.application.phonecontact.usecase;

import java.util.List;

import com.backintro.application.phonecontact.dto.PhoneContactResponse;
import com.backintro.domain.phonecontact.port.repository.PhoneContactRepository;

public class ListPhoneContactUseCase {

    private final PhoneContactRepository phoneContactRepository;

    public ListPhoneContactUseCase(
            PhoneContactRepository phoneContactRepository
    ) {
        this.phoneContactRepository = phoneContactRepository;
    }

    public List<PhoneContactResponse> execute() {
        return phoneContactRepository.findAll()
                .stream()
                .map(phoneContact ->
                        new PhoneContactResponse(
                                phoneContact.id().value(),
                                phoneContact.phone(),
                                phoneContact.notes()
                        )
                )
                .toList();
    }
}
