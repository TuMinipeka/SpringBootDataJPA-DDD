package com.backintro.infrastructure.professionalstudy.adapters.in.rest.controllers;

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

import com.backintro.application.professionalstudy.command.RegisterProfessionalStudyCommand;
import com.backintro.application.professionalstudy.command.UpdateProfessionalStudyCommand;
import com.backintro.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.backintro.application.professionalstudy.usecase.DeleteProfessionalStudyUseCase;
import com.backintro.application.professionalstudy.usecase.GetProfessionalStudyByIdUseCase;
import com.backintro.application.professionalstudy.usecase.ListProfessionalStudyUseCase;
import com.backintro.application.professionalstudy.usecase.RegisterProfessionalStudyUseCase;
import com.backintro.application.professionalstudy.usecase.UpdateProfessionalStudyUseCase;
import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.backintro.domain.study.model.valueobject.StudyId;
import com.backintro.infrastructure.professionalstudy.adapters.in.rest.dtos.CreateProfessionalStudyRequest;
import com.backintro.infrastructure.professionalstudy.adapters.in.rest.dtos.UpdateProfessionalStudyRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/professional-studies")
public class ProfessionalStudyController {

    private final RegisterProfessionalStudyUseCase registerUseCase;
    private final GetProfessionalStudyByIdUseCase getByIdUseCase;
    private final ListProfessionalStudyUseCase listUseCase;
    private final UpdateProfessionalStudyUseCase updateUseCase;
    private final DeleteProfessionalStudyUseCase deleteUseCase;

    public ProfessionalStudyController(
            RegisterProfessionalStudyUseCase registerUseCase,
            GetProfessionalStudyByIdUseCase getByIdUseCase,
            ListProfessionalStudyUseCase listUseCase,
            UpdateProfessionalStudyUseCase updateUseCase,
            DeleteProfessionalStudyUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalStudyResponse> create(
            @Valid @RequestBody CreateProfessionalStudyRequest request
    ) {
        var command = new RegisterProfessionalStudyCommand(
                new StudyId(request.studyId()),
                new ProfessionalId(request.professionalId()),
                request.title(),
                request.university(),
                request.resolutionNumber(),
                toCountryId(request.countryId())
        );
        var response = registerUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalStudyResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalStudyResponse> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                getByIdUseCase.execute(new ProfessionalStudyId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalStudyResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProfessionalStudyRequest request
    ) {
        var command = new UpdateProfessionalStudyCommand(
                new ProfessionalStudyId(id),
                request.title(),
                request.university(),
                request.valid(),
                request.resolutionNumber()
        );

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ProfessionalStudyId(id));

        return ResponseEntity.noContent().build();
    }

    private CountryId toCountryId(UUID countryId) {
        return countryId == null ? null : new CountryId(countryId);
    }
}
