package com.backintro.application.contact.usecase;

import java.util.List;

import com.backintro.application.contact.dto.ContactResponse;
import com.backintro.domain.contact.port.repository.ContactRepository;

public class ListContactUseCase {

    private final ContactRepository contactRepository;

    public ListContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactResponse> execute() {
        return contactRepository.findAll()
                .stream()
                .map(contact ->
                        new ContactResponse(
                                contact.id().value(),
                                contact.fullName(),
                                contact.email(),
                                contact.notes(),
                                contact.cityId() == null
                                        ? null
                                        : contact.cityId().value()
                        )
                )
                .toList();
    }
}
