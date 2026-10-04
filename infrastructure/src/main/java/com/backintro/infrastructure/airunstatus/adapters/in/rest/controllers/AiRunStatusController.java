package com.backintro.infrastructure.airunstatus.adapters.in.rest.controllers;

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

import com.backintro.application.airunstatus.command.RegisterAiRunStatusCommand;
import com.backintro.application.airunstatus.command.UpdateAiRunStatusCommand;
import com.backintro.application.airunstatus.dto.AiRunStatusResponse;
import com.backintro.application.airunstatus.usecase.DeleteAiRunStatusUseCase;
import com.backintro.application.airunstatus.usecase.GetAiRunStatusByIdUseCase;
import com.backintro.application.airunstatus.usecase.ListAiRunStatusUseCase;
import com.backintro.application.airunstatus.usecase.RegisterAiRunStatusUseCase;
import com.backintro.application.airunstatus.usecase.UpdateAiRunStatusUseCase;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.infrastructure.airunstatus.adapters.in.rest.dtos.CreateAiRunStatusRequest;
import com.backintro.infrastructure.airunstatus.adapters.in.rest.dtos.UpdateAiRunStatusRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ai-run-statuses")
public class AiRunStatusController {

    private final RegisterAiRunStatusUseCase registerUseCase;
    private final GetAiRunStatusByIdUseCase getByIdUseCase;
    private final ListAiRunStatusUseCase listUseCase;
    private final UpdateAiRunStatusUseCase updateUseCase;
    private final DeleteAiRunStatusUseCase deleteUseCase;

    public AiRunStatusController(
            RegisterAiRunStatusUseCase registerUseCase,
            GetAiRunStatusByIdUseCase getByIdUseCase,
            ListAiRunStatusUseCase listUseCase,
            UpdateAiRunStatusUseCase updateUseCase,
            DeleteAiRunStatusUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AiRunStatusResponse> create(
            @Valid @RequestBody CreateAiRunStatusRequest request
    ) {
        var command =
                new RegisterAiRunStatusCommand(request.nameStatus());
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<AiRunStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiRunStatusResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new AiRunStatusId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiRunStatusResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateAiRunStatusRequest request
    ) {
        var command = new UpdateAiRunStatusCommand(
                new AiRunStatusId(id),
                request.nameStatus()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new AiRunStatusId(id));

        return ResponseEntity.noContent().build();
    }
}
