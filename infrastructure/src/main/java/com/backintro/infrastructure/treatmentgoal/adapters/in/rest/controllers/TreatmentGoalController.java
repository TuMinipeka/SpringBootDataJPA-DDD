package com.backintro.infrastructure.treatmentgoal.adapters.in.rest.controllers;

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

import com.backintro.application.treatmentgoal.command.RegisterTreatmentGoalCommand;
import com.backintro.application.treatmentgoal.command.UpdateTreatmentGoalCommand;
import com.backintro.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.backintro.application.treatmentgoal.usecase.DeleteTreatmentGoalUseCase;
import com.backintro.application.treatmentgoal.usecase.GetTreatmentGoalByIdUseCase;
import com.backintro.application.treatmentgoal.usecase.ListTreatmentGoalUseCase;
import com.backintro.application.treatmentgoal.usecase.RegisterTreatmentGoalUseCase;
import com.backintro.application.treatmentgoal.usecase.UpdateTreatmentGoalUseCase;
import com.backintro.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.infrastructure.treatmentgoal.adapters.in.rest.dtos.CreateTreatmentGoalRequest;
import com.backintro.infrastructure.treatmentgoal.adapters.in.rest.dtos.UpdateTreatmentGoalRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/treatment-goals")
public class TreatmentGoalController {

    private final RegisterTreatmentGoalUseCase registerUseCase;
    private final GetTreatmentGoalByIdUseCase getByIdUseCase;
    private final ListTreatmentGoalUseCase listUseCase;
    private final UpdateTreatmentGoalUseCase updateUseCase;
    private final DeleteTreatmentGoalUseCase deleteUseCase;

    public TreatmentGoalController(
            RegisterTreatmentGoalUseCase registerUseCase,
            GetTreatmentGoalByIdUseCase getByIdUseCase,
            ListTreatmentGoalUseCase listUseCase,
            UpdateTreatmentGoalUseCase updateUseCase,
            DeleteTreatmentGoalUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<TreatmentGoalResponse> create(
            @Valid @RequestBody CreateTreatmentGoalRequest request
    ) {
        var command = new RegisterTreatmentGoalCommand(
                new TreatmentPlanId(request.treatmentPlanId()),
                request.description(),
                request.targetDate(),
                request.notes(),
                new TreatmentGoalStatusId(request.statusId())
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TreatmentGoalResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentGoalResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new TreatmentGoalId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreatmentGoalResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTreatmentGoalRequest request
    ) {
        var command = new UpdateTreatmentGoalCommand(
                new TreatmentGoalId(id),
                request.description(),
                request.targetDate(),
                request.completedAt(),
                request.notes(),
                new TreatmentGoalStatusId(request.statusId())
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new TreatmentGoalId(id));

        return ResponseEntity.noContent().build();
    }
}
