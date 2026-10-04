package com.backintro.domain.patient.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.gender.model.valueobject.GenderId;
import com.backintro.domain.patient.event.PatientRegisteredEvent;
import com.backintro.domain.patient.event.PatientUpdatedEvent;
import com.backintro.domain.patient.model.valueobject.PatientId;

public class Patient extends AggregateRoot {

    private final PatientId id;
    private final DocumentTypeId documentTypeId;
    private final GenderId biologicalSexId;
    private final GenderId genderIdentityId;
    private final CityMunicipalityId cityId;
    private String documentNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String address;
    private boolean active;

    private Patient(
            PatientId id,
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            GenderId biologicalSexId,
            GenderId genderIdentityId,
            String email,
            String phone,
            String address,
            boolean active,
            CityMunicipalityId cityId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.documentTypeId = Objects.requireNonNull(
                documentTypeId,
                "documentTypeId must not be null"
        );
        this.documentNumber = Objects.requireNonNull(
                documentNumber,
                "documentNumber must not be null"
        );
        this.firstName = Objects.requireNonNull(
                firstName,
                "firstName must not be null"
        );
        this.middleName = middleName;
        this.lastName = Objects.requireNonNull(
                lastName,
                "lastName must not be null"
        );
        this.secondLastName = secondLastName;
        this.birthDate = Objects.requireNonNull(
                birthDate,
                "birthDate must not be null"
        );
        this.biologicalSexId = biologicalSexId;
        this.genderIdentityId = genderIdentityId;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.cityId = cityId;
    }

    public static Patient register(
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            GenderId biologicalSexId,
            GenderId genderIdentityId,
            String email,
            String phone,
            String address,
            CityMunicipalityId cityId
    ) {
        PatientId id = PatientId.generate();
        Patient patient = new Patient(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                middleName,
                lastName,
                secondLastName,
                birthDate,
                biologicalSexId,
                genderIdentityId,
                email,
                phone,
                address,
                true,
                cityId
        );

        patient.recordEvent(
                new PatientRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return patient;
    }

    public static Patient restore(
            PatientId id,
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            GenderId biologicalSexId,
            GenderId genderIdentityId,
            String email,
            String phone,
            String address,
            boolean active,
            CityMunicipalityId cityId
    ) {
        return new Patient(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                middleName,
                lastName,
                secondLastName,
                birthDate,
                biologicalSexId,
                genderIdentityId,
                email,
                phone,
                address,
                active,
                cityId
        );
    }

    public void update(
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            String email,
            String phone,
            String address
    ) {
        this.documentNumber = Objects.requireNonNull(
                documentNumber,
                "documentNumber must not be null"
        );
        this.firstName = Objects.requireNonNull(
                firstName,
                "firstName must not be null"
        );
        this.middleName = middleName;
        this.lastName = Objects.requireNonNull(
                lastName,
                "lastName must not be null"
        );
        this.secondLastName = secondLastName;
        this.birthDate = Objects.requireNonNull(
                birthDate,
                "birthDate must not be null"
        );
        this.email = email;
        this.phone = phone;
        this.address = address;

        recordEvent(
                new PatientUpdatedEvent(
                        this.id,
                        this.documentNumber,
                        this.firstName,
                        this.middleName,
                        this.lastName,
                        this.secondLastName,
                        this.birthDate,
                        this.email,
                        this.phone,
                        this.address,
                        LocalDateTime.now()
                )
        );
    }

    public PatientId id() {
        return id;
    }

    public DocumentTypeId documentTypeId() {
        return documentTypeId;
    }

    public String documentNumber() {
        return documentNumber;
    }

    public String firstName() {
        return firstName;
    }

    public String middleName() {
        return middleName;
    }

    public String lastName() {
        return lastName;
    }

    public String secondLastName() {
        return secondLastName;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public GenderId biologicalSexId() {
        return biologicalSexId;
    }

    public GenderId genderIdentityId() {
        return genderIdentityId;
    }

    public String email() {
        return email;
    }

    public String phone() {
        return phone;
    }

    public String address() {
        return address;
    }

    public boolean active() {
        return active;
    }

    public CityMunicipalityId cityId() {
        return cityId;
    }
}
