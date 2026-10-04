package com.backintro.infrastructure.professionaltype.adapters.in.rest.controllers;

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

import com.backintro.application.professionaltype.command.RegisterProfessionalTypeCommand;
import com.backintro.application.professionaltype.command.UpdateProfessionalTypeCommand;
import com.backintro.application.professionaltype.dto.ProfessionalTypeResponse;
import com.backintro.application.professionaltype.usecase.DeleteProfessionalTypeUseCase;
import com.backintro.application.professionaltype.usecase.GetProfessionalTypeByIdUseCase;
import com.backintro.application.professionaltype.usecase.ListProfessionalTypeUseCase;
import com.backintro.application.professionaltype.usecase.RegisterProfessionalTypeUseCase;
import com.backintro.application.professionaltype.usecase.UpdateProfessionalTypeUseCase;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.backintro.infrastructure.professionaltype.adapters.in.rest.dtos.CreateProfessionalTypeRequest;
import com.backintro.infrastructure.professionaltype.adapters.in.rest.dtos.UpdateProfessionalTypeRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/professional-types")
public class ProfessionalTypeController {

    private final RegisterProfessionalTypeUseCase registerUseCase;
    private final GetProfessionalTypeByIdUseCase getByIdUseCase;
    private final ListProfessionalTypeUseCase listUseCase;
    private final UpdateProfessionalTypeUseCase updateUseCase;
    private final DeleteProfessionalTypeUseCase deleteUseCase;

    public ProfessionalTypeController(
            RegisterProfessionalTypeUseCase registerUseCase,
            GetProfessionalTypeByIdUseCase getByIdUseCase,
            ListProfessionalTypeUseCase listUseCase,
            UpdateProfessionalTypeUseCase updateUseCase,
            DeleteProfessionalTypeUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalTypeResponse> create(
            @Valid @RequestBody CreateProfessionalTypeRequest request
    ) {
        var command = new RegisterProfessionalTypeCommand(request.name());
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalTypeResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new ProfessionalTypeId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalTypeResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProfessionalTypeRequest request
    ) {
        var command = new UpdateProfessionalTypeCommand(
                new ProfessionalTypeId(id),
                request.name()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ProfessionalTypeId(id));

        return ResponseEntity.noContent().build();
    }
}
