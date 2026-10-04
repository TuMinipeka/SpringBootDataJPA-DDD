package com.backintro.infrastructure.diagnosticsystem.adapters.in.rest.controllers;

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

import com.backintro.application.diagnosticsystem.command.RegisterDiagnosticSystemCommand;
import com.backintro.application.diagnosticsystem.command.UpdateDiagnosticSystemCommand;
import com.backintro.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.backintro.application.diagnosticsystem.usecase.DeleteDiagnosticSystemUseCase;
import com.backintro.application.diagnosticsystem.usecase.GetDiagnosticSystemByIdUseCase;
import com.backintro.application.diagnosticsystem.usecase.ListDiagnosticSystemUseCase;
import com.backintro.application.diagnosticsystem.usecase.RegisterDiagnosticSystemUseCase;
import com.backintro.application.diagnosticsystem.usecase.UpdateDiagnosticSystemUseCase;
import com.backintro.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.backintro.infrastructure.diagnosticsystem.adapters.in.rest.dtos.CreateDiagnosticSystemRequest;
import com.backintro.infrastructure.diagnosticsystem.adapters.in.rest.dtos.UpdateDiagnosticSystemRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/diagnostic-systems")
public class DiagnosticSystemController {

    private final RegisterDiagnosticSystemUseCase registerUseCase;
    private final GetDiagnosticSystemByIdUseCase getByIdUseCase;
    private final ListDiagnosticSystemUseCase listUseCase;
    private final UpdateDiagnosticSystemUseCase updateUseCase;
    private final DeleteDiagnosticSystemUseCase deleteUseCase;

    public DiagnosticSystemController(
            RegisterDiagnosticSystemUseCase registerUseCase,
            GetDiagnosticSystemByIdUseCase getByIdUseCase,
            ListDiagnosticSystemUseCase listUseCase,
            UpdateDiagnosticSystemUseCase updateUseCase,
            DeleteDiagnosticSystemUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<DiagnosticSystemResponse> create(
            @Valid @RequestBody CreateDiagnosticSystemRequest request
    ) {
        var command = new RegisterDiagnosticSystemCommand(
                request.name(),
                request.code(),
                request.version()
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticSystemResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticSystemResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new DiagnosticSystemId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticSystemResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateDiagnosticSystemRequest request
    ) {
        var command = new UpdateDiagnosticSystemCommand(
                new DiagnosticSystemId(id),
                request.name(),
                request.code(),
                request.version()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new DiagnosticSystemId(id));

        return ResponseEntity.noContent().build();
    }
}
