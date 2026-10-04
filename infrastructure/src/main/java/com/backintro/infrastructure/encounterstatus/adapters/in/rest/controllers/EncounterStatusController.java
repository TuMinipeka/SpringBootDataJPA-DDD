package com.backintro.infrastructure.encounterstatus.adapters.in.rest.controllers;

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

import com.backintro.application.encounterstatus.command.RegisterEncounterStatusCommand;
import com.backintro.application.encounterstatus.command.UpdateEncounterStatusCommand;
import com.backintro.application.encounterstatus.dto.EncounterStatusResponse;
import com.backintro.application.encounterstatus.usecase.DeleteEncounterStatusUseCase;
import com.backintro.application.encounterstatus.usecase.GetEncounterStatusByIdUseCase;
import com.backintro.application.encounterstatus.usecase.ListEncounterStatusUseCase;
import com.backintro.application.encounterstatus.usecase.RegisterEncounterStatusUseCase;
import com.backintro.application.encounterstatus.usecase.UpdateEncounterStatusUseCase;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.infrastructure.encounterstatus.adapters.in.rest.dtos.CreateEncounterStatusRequest;
import com.backintro.infrastructure.encounterstatus.adapters.in.rest.dtos.UpdateEncounterStatusRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/encounter-statuses")
public class EncounterStatusController {

    private final RegisterEncounterStatusUseCase registerUseCase;
    private final GetEncounterStatusByIdUseCase getByIdUseCase;
    private final ListEncounterStatusUseCase listUseCase;
    private final UpdateEncounterStatusUseCase updateUseCase;
    private final DeleteEncounterStatusUseCase deleteUseCase;

    public EncounterStatusController(
            RegisterEncounterStatusUseCase registerUseCase,
            GetEncounterStatusByIdUseCase getByIdUseCase,
            ListEncounterStatusUseCase listUseCase,
            UpdateEncounterStatusUseCase updateUseCase,
            DeleteEncounterStatusUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<EncounterStatusResponse> create(
            @Valid @RequestBody CreateEncounterStatusRequest request
    ) {
        var command = new RegisterEncounterStatusCommand(
                request.name(),
                request.code()
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EncounterStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EncounterStatusResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new EncounterStatusId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EncounterStatusResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateEncounterStatusRequest request
    ) {
        var command = new UpdateEncounterStatusCommand(
                new EncounterStatusId(id),
                request.name(),
                request.code()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new EncounterStatusId(id));

        return ResponseEntity.noContent().build();
    }
}
