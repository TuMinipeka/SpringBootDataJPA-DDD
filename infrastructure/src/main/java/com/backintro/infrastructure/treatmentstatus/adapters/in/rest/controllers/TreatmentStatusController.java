package com.backintro.infrastructure.treatmentstatus.adapters.in.rest.controllers;

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

import com.backintro.application.treatmentstatus.command.RegisterTreatmentStatusCommand;
import com.backintro.application.treatmentstatus.command.UpdateTreatmentStatusCommand;
import com.backintro.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.backintro.application.treatmentstatus.usecase.DeleteTreatmentStatusUseCase;
import com.backintro.application.treatmentstatus.usecase.GetTreatmentStatusByIdUseCase;
import com.backintro.application.treatmentstatus.usecase.ListTreatmentStatusUseCase;
import com.backintro.application.treatmentstatus.usecase.RegisterTreatmentStatusUseCase;
import com.backintro.application.treatmentstatus.usecase.UpdateTreatmentStatusUseCase;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.backintro.infrastructure.treatmentstatus.adapters.in.rest.dtos.CreateTreatmentStatusRequest;
import com.backintro.infrastructure.treatmentstatus.adapters.in.rest.dtos.UpdateTreatmentStatusRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/treatment-statuses")
public class TreatmentStatusController {

    private final RegisterTreatmentStatusUseCase registerUseCase;
    private final GetTreatmentStatusByIdUseCase getByIdUseCase;
    private final ListTreatmentStatusUseCase listUseCase;
    private final UpdateTreatmentStatusUseCase updateUseCase;
    private final DeleteTreatmentStatusUseCase deleteUseCase;

    public TreatmentStatusController(
            RegisterTreatmentStatusUseCase registerUseCase,
            GetTreatmentStatusByIdUseCase getByIdUseCase,
            ListTreatmentStatusUseCase listUseCase,
            UpdateTreatmentStatusUseCase updateUseCase,
            DeleteTreatmentStatusUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<TreatmentStatusResponse> create(
            @Valid @RequestBody CreateTreatmentStatusRequest request
    ) {
        var command = new RegisterTreatmentStatusCommand(
                request.name(),
                request.code()
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TreatmentStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentStatusResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new TreatmentStatusId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreatmentStatusResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTreatmentStatusRequest request
    ) {
        var command = new UpdateTreatmentStatusCommand(
                new TreatmentStatusId(id),
                request.name(),
                request.code()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new TreatmentStatusId(id));

        return ResponseEntity.noContent().build();
    }
}
