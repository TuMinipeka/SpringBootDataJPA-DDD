package com.backintro.infrastructure.aiprovidermodel.adapters.in.rest.controllers;

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

import com.backintro.application.aiprovidermodel.command.RegisterAiProviderModelCommand;
import com.backintro.application.aiprovidermodel.command.UpdateAiProviderModelCommand;
import com.backintro.application.aiprovidermodel.dto.AiProviderModelResponse;
import com.backintro.application.aiprovidermodel.usecase.DeleteAiProviderModelUseCase;
import com.backintro.application.aiprovidermodel.usecase.GetAiProviderModelByIdUseCase;
import com.backintro.application.aiprovidermodel.usecase.ListAiProviderModelUseCase;
import com.backintro.application.aiprovidermodel.usecase.RegisterAiProviderModelUseCase;
import com.backintro.application.aiprovidermodel.usecase.UpdateAiProviderModelUseCase;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;
import com.backintro.infrastructure.aiprovidermodel.adapters.in.rest.dtos.CreateAiProviderModelRequest;
import com.backintro.infrastructure.aiprovidermodel.adapters.in.rest.dtos.UpdateAiProviderModelRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ai-provider-models")
public class AiProviderModelController {

    private final RegisterAiProviderModelUseCase registerUseCase;
    private final GetAiProviderModelByIdUseCase getByIdUseCase;
    private final ListAiProviderModelUseCase listUseCase;
    private final UpdateAiProviderModelUseCase updateUseCase;
    private final DeleteAiProviderModelUseCase deleteUseCase;

    public AiProviderModelController(
            RegisterAiProviderModelUseCase registerUseCase,
            GetAiProviderModelByIdUseCase getByIdUseCase,
            ListAiProviderModelUseCase listUseCase,
            UpdateAiProviderModelUseCase updateUseCase,
            DeleteAiProviderModelUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AiProviderModelResponse> create(
            @Valid @RequestBody CreateAiProviderModelRequest request
    ) {
        var command =
                new RegisterAiProviderModelCommand(
                        request.nameProviderAi(),
                        request.razonSocial(),
                        request.sitioWeb()
                );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<AiProviderModelResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiProviderModelResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new AiProviderModelId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiProviderModelResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateAiProviderModelRequest request
    ) {
        var command = new UpdateAiProviderModelCommand(
                new AiProviderModelId(id),
                request.nameProviderAi(),
                request.razonSocial(),
                request.sitioWeb()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new AiProviderModelId(id));

        return ResponseEntity.noContent().build();
    }
}
