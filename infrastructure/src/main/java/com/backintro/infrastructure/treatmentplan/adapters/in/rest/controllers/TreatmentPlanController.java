package com.backintro.infrastructure.treatmentplan.adapters.in.rest.controllers;

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

import com.backintro.application.treatmentplan.command.RegisterTreatmentPlanCommand;
import com.backintro.application.treatmentplan.command.UpdateTreatmentPlanCommand;
import com.backintro.application.treatmentplan.dto.TreatmentPlanResponse;
import com.backintro.application.treatmentplan.usecase.DeleteTreatmentPlanUseCase;
import com.backintro.application.treatmentplan.usecase.GetTreatmentPlanByIdUseCase;
import com.backintro.application.treatmentplan.usecase.ListTreatmentPlanUseCase;
import com.backintro.application.treatmentplan.usecase.RegisterTreatmentPlanUseCase;
import com.backintro.application.treatmentplan.usecase.UpdateTreatmentPlanUseCase;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.backintro.infrastructure.treatmentplan.adapters.in.rest.dtos.CreateTreatmentPlanRequest;
import com.backintro.infrastructure.treatmentplan.adapters.in.rest.dtos.UpdateTreatmentPlanRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/treatment-plans")
public class TreatmentPlanController {

    private final RegisterTreatmentPlanUseCase registerUseCase;
    private final GetTreatmentPlanByIdUseCase getByIdUseCase;
    private final ListTreatmentPlanUseCase listUseCase;
    private final UpdateTreatmentPlanUseCase updateUseCase;
    private final DeleteTreatmentPlanUseCase deleteUseCase;

    public TreatmentPlanController(
            RegisterTreatmentPlanUseCase registerUseCase,
            GetTreatmentPlanByIdUseCase getByIdUseCase,
            ListTreatmentPlanUseCase listUseCase,
            UpdateTreatmentPlanUseCase updateUseCase,
            DeleteTreatmentPlanUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<TreatmentPlanResponse> create(
            @Valid @RequestBody CreateTreatmentPlanRequest request
    ) {
        var command = new RegisterTreatmentPlanCommand(
                new EncounterId(request.encounterId()),
                new ProfessionalId(request.professionalId()),
                request.title(),
                request.description(),
                request.startDate(),
                new TreatmentStatusId(request.statusId())
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TreatmentPlanResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentPlanResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new TreatmentPlanId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreatmentPlanResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTreatmentPlanRequest request
    ) {
        var command = new UpdateTreatmentPlanCommand(
                new TreatmentPlanId(id),
                request.title(),
                request.description(),
                request.endDate(),
                new TreatmentStatusId(request.statusId())
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new TreatmentPlanId(id));

        return ResponseEntity.noContent().build();
    }
}
