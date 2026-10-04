package com.backintro.infrastructure.phonecontact.adapters.in.rest.controllers;

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

import com.backintro.application.phonecontact.command.RegisterPhoneContactCommand;
import com.backintro.application.phonecontact.command.UpdatePhoneContactCommand;
import com.backintro.application.phonecontact.dto.PhoneContactResponse;
import com.backintro.application.phonecontact.usecase.DeletePhoneContactUseCase;
import com.backintro.application.phonecontact.usecase.GetPhoneContactByIdUseCase;
import com.backintro.application.phonecontact.usecase.ListPhoneContactUseCase;
import com.backintro.application.phonecontact.usecase.RegisterPhoneContactUseCase;
import com.backintro.application.phonecontact.usecase.UpdatePhoneContactUseCase;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;
import com.backintro.infrastructure.phonecontact.adapters.in.rest.dtos.CreatePhoneContactRequest;
import com.backintro.infrastructure.phonecontact.adapters.in.rest.dtos.UpdatePhoneContactRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/phone-contacts")
public class PhoneContactController {

    private final RegisterPhoneContactUseCase registerUseCase;
    private final GetPhoneContactByIdUseCase getByIdUseCase;
    private final ListPhoneContactUseCase listUseCase;
    private final UpdatePhoneContactUseCase updateUseCase;
    private final DeletePhoneContactUseCase deleteUseCase;

    public PhoneContactController(
            RegisterPhoneContactUseCase registerUseCase,
            GetPhoneContactByIdUseCase getByIdUseCase,
            ListPhoneContactUseCase listUseCase,
            UpdatePhoneContactUseCase updateUseCase,
            DeletePhoneContactUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<PhoneContactResponse> create(
            @Valid @RequestBody CreatePhoneContactRequest request
    ) {
        var command = new RegisterPhoneContactCommand(
                new ContactId(request.contactId()),
                request.phone(),
                request.notes()
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PhoneContactResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneContactResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new PhoneContactId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneContactResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePhoneContactRequest request
    ) {
        var command = new UpdatePhoneContactCommand(
                new PhoneContactId(id),
                request.phone(),
                request.notes()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new PhoneContactId(id));

        return ResponseEntity.noContent().build();
    }
}
