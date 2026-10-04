package com.backintro.infrastructure.stateregion.adapters.in.rest.controllers;

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

import com.backintro.application.stateregion.command.RegisterStateRegionCommand;
import com.backintro.application.stateregion.command.UpdateStateRegionCommand;
import com.backintro.application.stateregion.dto.StateRegionResponse;
import com.backintro.application.stateregion.usecase.DeleteStateRegionUseCase;
import com.backintro.application.stateregion.usecase.GetStateRegionByIdUseCase;
import com.backintro.application.stateregion.usecase.ListStateRegionUseCase;
import com.backintro.application.stateregion.usecase.RegisterStateRegionUseCase;
import com.backintro.application.stateregion.usecase.UpdateStateRegionUseCase;
import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;
import com.backintro.infrastructure.stateregion.adapters.in.rest.dtos.CreateStateRegionRequest;
import com.backintro.infrastructure.stateregion.adapters.in.rest.dtos.UpdateStateRegionRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/state-regions")
public class StateRegionController {

    private final RegisterStateRegionUseCase registerUseCase;
    private final GetStateRegionByIdUseCase getByIdUseCase;
    private final ListStateRegionUseCase listUseCase;
    private final UpdateStateRegionUseCase updateUseCase;
    private final DeleteStateRegionUseCase deleteUseCase;

    public StateRegionController(
            RegisterStateRegionUseCase registerUseCase,
            GetStateRegionByIdUseCase getByIdUseCase,
            ListStateRegionUseCase listUseCase,
            UpdateStateRegionUseCase updateUseCase,
            DeleteStateRegionUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<StateRegionResponse> create(
            @Valid @RequestBody CreateStateRegionRequest request
    ) {
        var command = new RegisterStateRegionCommand(
                new CountryId(request.countryId()),
                request.nameRegion(),
                request.codeRegion()
        );

        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<StateRegionResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateRegionResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new StateRegionId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateRegionResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateStateRegionRequest request
    ) {
        var command = new UpdateStateRegionCommand(
                new StateRegionId(id),
                request.nameRegion(),
                request.codeRegion()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new StateRegionId(id));

        return ResponseEntity.noContent().build();
    }
}
