package com.backintro.infrastructure.professional.adapters.in.rest.controllers;

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

import com.backintro.application.professional.command.RegisterProfessionalCommand;
import com.backintro.application.professional.command.UpdateProfessionalCommand;
import com.backintro.application.professional.dto.ProfessionalResponse;
import com.backintro.application.professional.usecase.DeleteProfessionalUseCase;
import com.backintro.application.professional.usecase.GetProfessionalByIdUseCase;
import com.backintro.application.professional.usecase.ListProfessionalUseCase;
import com.backintro.application.professional.usecase.RegisterProfessionalUseCase;
import com.backintro.application.professional.usecase.UpdateProfessionalUseCase;
import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.backintro.infrastructure.professional.adapters.in.rest.dtos.CreateProfessionalRequest;
import com.backintro.infrastructure.professional.adapters.in.rest.dtos.UpdateProfessionalRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    private final RegisterProfessionalUseCase registerUseCase;
    private final GetProfessionalByIdUseCase getByIdUseCase;
    private final ListProfessionalUseCase listUseCase;
    private final UpdateProfessionalUseCase updateUseCase;
    private final DeleteProfessionalUseCase deleteUseCase;

    public ProfessionalController(
            RegisterProfessionalUseCase registerUseCase,
            GetProfessionalByIdUseCase getByIdUseCase,
            ListProfessionalUseCase listUseCase,
            UpdateProfessionalUseCase updateUseCase,
            DeleteProfessionalUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalResponse> create(
            @Valid @RequestBody CreateProfessionalRequest request
    ) {
        var command = new RegisterProfessionalCommand(
                new DocumentTypeId(request.documentTypeId()),
                request.documentNumber(),
                request.firstName(),
                request.lastName(),
                new ProfessionalTypeId(request.professionalTypeId()),
                request.licenseNumber(),
                toCityId(request.cityId()),
                toContactId(request.contactId())
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new ProfessionalId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProfessionalRequest request
    ) {
        var command = new UpdateProfessionalCommand(
                new ProfessionalId(id),
                request.documentNumber(),
                request.firstName(),
                request.lastName(),
                request.licenseNumber()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ProfessionalId(id));

        return ResponseEntity.noContent().build();
    }

    private CityMunicipalityId toCityId(UUID cityId) {
        return cityId == null ? null : new CityMunicipalityId(cityId);
    }

    private ContactId toContactId(UUID contactId) {
        return contactId == null ? null : new ContactId(contactId);
    }
}
