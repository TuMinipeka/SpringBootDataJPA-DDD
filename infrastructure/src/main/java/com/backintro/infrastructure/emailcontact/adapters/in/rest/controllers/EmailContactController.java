package com.backintro.infrastructure.emailcontact.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backintro.application.emailcontact.command.RegisterEmailContactCommand;
import com.backintro.application.emailcontact.command.UpdateEmailContactCommand;
import com.backintro.application.emailcontact.dto.EmailContactResponse;
import com.backintro.application.emailcontact.usecase.DeleteEmailContactUseCase;
import com.backintro.application.emailcontact.usecase.GetEmailContactByIdUseCase;
import com.backintro.application.emailcontact.usecase.ListEmailContactUseCase;
import com.backintro.application.emailcontact.usecase.RegisterEmailContactUseCase;
import com.backintro.application.emailcontact.usecase.UpdateEmailContactUseCase;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.emailcontact.model.valueobject.EmailContactId;
import com.backintro.infrastructure.emailcontact.adapters.in.rest.dtos.CreateEmailContactRequest;
import com.backintro.infrastructure.emailcontact.adapters.in.rest.dtos.UpdateEmailContactRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/email-contacts")
public class EmailContactController {

    private final RegisterEmailContactUseCase registerUseCase;
    private final GetEmailContactByIdUseCase getByIdUseCase;
    private final ListEmailContactUseCase listUseCase;
    private final UpdateEmailContactUseCase updateUseCase;
    private final DeleteEmailContactUseCase deleteUseCase;

    public EmailContactController(
            RegisterEmailContactUseCase registerUseCase,
            GetEmailContactByIdUseCase getByIdUseCase,
            ListEmailContactUseCase listUseCase,
            UpdateEmailContactUseCase updateUseCase,
            DeleteEmailContactUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<EmailContactResponse> create(
            @Valid @RequestBody CreateEmailContactRequest request
    ) {
        var command = new RegisterEmailContactCommand(
                new ContactId(request.contactId()),
                request.email(),
                request.notes()
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmailContactResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailContactResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new EmailContactId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailContactResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateEmailContactRequest request
    ) {
        var command = new UpdateEmailContactCommand(
                new EmailContactId(id),
                request.email(),
                request.notes()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new EmailContactId(id));

        return ResponseEntity.noContent().build();
    }
}
