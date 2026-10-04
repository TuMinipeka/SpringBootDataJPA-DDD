package com.backintro.application.emailcontact.usecase;

import java.util.List;

import com.backintro.application.emailcontact.dto.EmailContactResponse;
import com.backintro.domain.emailcontact.port.repository.EmailContactRepository;

public class ListEmailContactUseCase {

    private final EmailContactRepository emailContactRepository;

    public ListEmailContactUseCase(
            EmailContactRepository emailContactRepository
    ) {
        this.emailContactRepository = emailContactRepository;
    }

    public List<EmailContactResponse> execute() {
        return emailContactRepository.findAll()
                .stream()
                .map(emailContact ->
                        new EmailContactResponse(
                                emailContact.id().value(),
                                emailContact.email(),
                                emailContact.notes()
                        )
                )
                .toList();
    }
}
