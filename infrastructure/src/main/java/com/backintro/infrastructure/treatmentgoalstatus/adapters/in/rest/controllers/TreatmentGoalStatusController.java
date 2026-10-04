package com.backintro.infrastructure.treatmentgoalstatus.adapters.in.rest.controllers;

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

import com.backintro.application.treatmentgoalstatus.command.RegisterTreatmentGoalStatusCommand;
import com.backintro.application.treatmentgoalstatus.command.UpdateTreatmentGoalStatusCommand;
import com.backintro.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.backintro.application.treatmentgoalstatus.usecase.DeleteTreatmentGoalStatusUseCase;
import com.backintro.application.treatmentgoalstatus.usecase.GetTreatmentGoalStatusByIdUseCase;
import com.backintro.application.treatmentgoalstatus.usecase.ListTreatmentGoalStatusUseCase;
import com.backintro.application.treatmentgoalstatus.usecase.RegisterTreatmentGoalStatusUseCase;
import com.backintro.application.treatmentgoalstatus.usecase.UpdateTreatmentGoalStatusUseCase;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.infrastructure.treatmentgoalstatus.adapters.in.rest.dtos.CreateTreatmentGoalStatusRequest;
import com.backintro.infrastructure.treatmentgoalstatus.adapters.in.rest.dtos.UpdateTreatmentGoalStatusRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/treatment-goal-statuses")
public class TreatmentGoalStatusController {

    private final RegisterTreatmentGoalStatusUseCase registerUseCase;
    private final GetTreatmentGoalStatusByIdUseCase getByIdUseCase;
    private final ListTreatmentGoalStatusUseCase listUseCase;
    private final UpdateTreatmentGoalStatusUseCase updateUseCase;
    private final DeleteTreatmentGoalStatusUseCase deleteUseCase;

    public TreatmentGoalStatusController(
            RegisterTreatmentGoalStatusUseCase registerUseCase,
            GetTreatmentGoalStatusByIdUseCase getByIdUseCase,
            ListTreatmentGoalStatusUseCase listUseCase,
            UpdateTreatmentGoalStatusUseCase updateUseCase,
            DeleteTreatmentGoalStatusUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<TreatmentGoalStatusResponse> create(
            @Valid @RequestBody CreateTreatmentGoalStatusRequest request
    ) {
        var command = new RegisterTreatmentGoalStatusCommand(
                request.name(),
                request.code()
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TreatmentGoalStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentGoalStatusResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new TreatmentGoalStatusId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreatmentGoalStatusResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTreatmentGoalStatusRequest request
    ) {
        var command = new UpdateTreatmentGoalStatusCommand(
                new TreatmentGoalStatusId(id),
                request.name(),
                request.code()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new TreatmentGoalStatusId(id));

        return ResponseEntity.noContent().build();
    }
}
