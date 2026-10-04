package com.backintro.infrastructure.encountertype.adapters.in.rest.controllers;

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

import com.backintro.application.encountertype.command.RegisterEncounterTypeCommand;
import com.backintro.application.encountertype.command.UpdateEncounterTypeCommand;
import com.backintro.application.encountertype.dto.EncounterTypeResponse;
import com.backintro.application.encountertype.usecase.DeleteEncounterTypeUseCase;
import com.backintro.application.encountertype.usecase.GetEncounterTypeByIdUseCase;
import com.backintro.application.encountertype.usecase.ListEncounterTypeUseCase;
import com.backintro.application.encountertype.usecase.RegisterEncounterTypeUseCase;
import com.backintro.application.encountertype.usecase.UpdateEncounterTypeUseCase;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.infrastructure.encountertype.adapters.in.rest.dtos.CreateEncounterTypeRequest;
import com.backintro.infrastructure.encountertype.adapters.in.rest.dtos.UpdateEncounterTypeRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/encounter-types")
public class EncounterTypeController {

    private final RegisterEncounterTypeUseCase registerUseCase;
    private final GetEncounterTypeByIdUseCase getByIdUseCase;
    private final ListEncounterTypeUseCase listUseCase;
    private final UpdateEncounterTypeUseCase updateUseCase;
    private final DeleteEncounterTypeUseCase deleteUseCase;

    public EncounterTypeController(
            RegisterEncounterTypeUseCase registerUseCase,
            GetEncounterTypeByIdUseCase getByIdUseCase,
            ListEncounterTypeUseCase listUseCase,
            UpdateEncounterTypeUseCase updateUseCase,
            DeleteEncounterTypeUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<EncounterTypeResponse> create(
            @Valid @RequestBody CreateEncounterTypeRequest request
    ) {
        var command = new RegisterEncounterTypeCommand(
                request.name(),
                request.code()
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EncounterTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EncounterTypeResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new EncounterTypeId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EncounterTypeResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateEncounterTypeRequest request
    ) {
        var command = new UpdateEncounterTypeCommand(
                new EncounterTypeId(id),
                request.name(),
                request.code()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new EncounterTypeId(id));

        return ResponseEntity.noContent().build();
    }
}
