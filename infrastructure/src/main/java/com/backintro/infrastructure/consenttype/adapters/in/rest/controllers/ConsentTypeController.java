package com.backintro.infrastructure.consenttype.adapters.in.rest.controllers;

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

import com.backintro.application.consenttype.command.RegisterConsentTypeCommand;
import com.backintro.application.consenttype.command.UpdateConsentTypeCommand;
import com.backintro.application.consenttype.dto.ConsentTypeResponse;
import com.backintro.application.consenttype.usecase.DeleteConsentTypeUseCase;
import com.backintro.application.consenttype.usecase.GetConsentTypeByIdUseCase;
import com.backintro.application.consenttype.usecase.ListConsentTypeUseCase;
import com.backintro.application.consenttype.usecase.RegisterConsentTypeUseCase;
import com.backintro.application.consenttype.usecase.UpdateConsentTypeUseCase;
import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;
import com.backintro.infrastructure.consenttype.adapters.in.rest.dtos.CreateConsentTypeRequest;
import com.backintro.infrastructure.consenttype.adapters.in.rest.dtos.UpdateConsentTypeRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/consent-types")
public class ConsentTypeController {

    private final RegisterConsentTypeUseCase registerUseCase;
    private final GetConsentTypeByIdUseCase getByIdUseCase;
    private final ListConsentTypeUseCase listUseCase;
    private final UpdateConsentTypeUseCase updateUseCase;
    private final DeleteConsentTypeUseCase deleteUseCase;

    public ConsentTypeController(
            RegisterConsentTypeUseCase registerUseCase,
            GetConsentTypeByIdUseCase getByIdUseCase,
            ListConsentTypeUseCase listUseCase,
            UpdateConsentTypeUseCase updateUseCase,
            DeleteConsentTypeUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ConsentTypeResponse> create(
            @Valid @RequestBody CreateConsentTypeRequest request
    ) {
        var command = new RegisterConsentTypeCommand(
                request.name(),
                request.code(),
                request.description()
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ConsentTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsentTypeResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new ConsentTypeId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsentTypeResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateConsentTypeRequest request
    ) {
        var command = new UpdateConsentTypeCommand(
                new ConsentTypeId(id),
                request.name(),
                request.code(),
                request.description()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ConsentTypeId(id));

        return ResponseEntity.noContent().build();
    }
}
