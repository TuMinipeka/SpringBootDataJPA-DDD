package com.backintro.infrastructure.patient.adapters.in.rest.controllers;

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

import com.backintro.application.patient.command.RegisterPatientCommand;
import com.backintro.application.patient.command.UpdatePatientCommand;
import com.backintro.application.patient.dto.PatientResponse;
import com.backintro.application.patient.usecase.DeletePatientUseCase;
import com.backintro.application.patient.usecase.GetPatientByIdUseCase;
import com.backintro.application.patient.usecase.ListPatientUseCase;
import com.backintro.application.patient.usecase.RegisterPatientUseCase;
import com.backintro.application.patient.usecase.UpdatePatientUseCase;
import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.gender.model.valueobject.GenderId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.infrastructure.patient.adapters.in.rest.dtos.CreatePatientRequest;
import com.backintro.infrastructure.patient.adapters.in.rest.dtos.UpdatePatientRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final RegisterPatientUseCase registerUseCase;
    private final GetPatientByIdUseCase getByIdUseCase;
    private final ListPatientUseCase listUseCase;
    private final UpdatePatientUseCase updateUseCase;
    private final DeletePatientUseCase deleteUseCase;

    public PatientController(
            RegisterPatientUseCase registerUseCase,
            GetPatientByIdUseCase getByIdUseCase,
            ListPatientUseCase listUseCase,
            UpdatePatientUseCase updateUseCase,
            DeletePatientUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> create(
            @Valid @RequestBody CreatePatientRequest request
    ) {
        var command = new RegisterPatientCommand(
                new DocumentTypeId(request.documentTypeId()),
                request.documentNumber(),
                request.firstName(),
                request.middleName(),
                request.lastName(),
                request.secondLastName(),
                request.birthDate(),
                toGenderId(request.biologicalSexId()),
                toGenderId(request.genderIdentityId()),
                request.email(),
                request.phone(),
                request.address(),
                toCityId(request.cityId())
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new PatientId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePatientRequest request
    ) {
        var command = new UpdatePatientCommand(
                new PatientId(id),
                request.documentNumber(),
                request.firstName(),
                request.middleName(),
                request.lastName(),
                request.secondLastName(),
                request.birthDate(),
                request.email(),
                request.phone(),
                request.address()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new PatientId(id));

        return ResponseEntity.noContent().build();
    }

    private GenderId toGenderId(UUID genderId) {
        return genderId == null ? null : new GenderId(genderId);
    }

    private CityMunicipalityId toCityId(UUID cityId) {
        return cityId == null ? null : new CityMunicipalityId(cityId);
    }
}
