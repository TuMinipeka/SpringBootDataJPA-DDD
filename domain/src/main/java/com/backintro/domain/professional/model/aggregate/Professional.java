package com.backintro.domain.professional.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.professional.event.ProfessionalRegisteredEvent;
import com.backintro.domain.professional.event.ProfessionalUpdatedEvent;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public class Professional extends AggregateRoot {

    private final ProfessionalId id;
    private final DocumentTypeId documentTypeId;
    private final ProfessionalTypeId professionalTypeId;
    private final CityMunicipalityId cityId;
    private final ContactId contactId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private boolean active;

    private Professional(
            ProfessionalId id,
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            ProfessionalTypeId professionalTypeId,
            String licenseNumber,
            boolean active,
            CityMunicipalityId cityId,
            ContactId contactId
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
        this.lastName = Objects.requireNonNull(
                lastName,
                "lastName must not be null"
        );
        this.professionalTypeId = Objects.requireNonNull(
                professionalTypeId,
                "professionalTypeId must not be null"
        );
        this.licenseNumber = licenseNumber;
        this.active = active;
        this.cityId = cityId;
        this.contactId = contactId;
    }

    public static Professional register(
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            ProfessionalTypeId professionalTypeId,
            String licenseNumber,
            CityMunicipalityId cityId,
            ContactId contactId
    ) {
        ProfessionalId id = ProfessionalId.generate();
        Professional professional = new Professional(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                lastName,
                professionalTypeId,
                licenseNumber,
                true,
                cityId,
                contactId
        );

        professional.recordEvent(
                new ProfessionalRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return professional;
    }

    public static Professional restore(
            ProfessionalId id,
            DocumentTypeId documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            ProfessionalTypeId professionalTypeId,
            String licenseNumber,
            boolean active,
            CityMunicipalityId cityId,
            ContactId contactId
    ) {
        return new Professional(
                id,
                documentTypeId,
                documentNumber,
                firstName,
                lastName,
                professionalTypeId,
                licenseNumber,
                active,
                cityId,
                contactId
        );
    }

    public void update(
            String documentNumber,
            String firstName,
            String lastName,
            String licenseNumber
    ) {
        this.documentNumber = Objects.requireNonNull(
                documentNumber,
                "documentNumber must not be null"
        );
        this.firstName = Objects.requireNonNull(
                firstName,
                "firstName must not be null"
        );
        this.lastName = Objects.requireNonNull(
                lastName,
                "lastName must not be null"
        );
        this.licenseNumber = licenseNumber;

        recordEvent(
                new ProfessionalUpdatedEvent(
                        this.id,
                        this.documentNumber,
                        this.firstName,
                        this.lastName,
                        this.licenseNumber,
                        LocalDateTime.now()
                )
        );
    }

    public ProfessionalId id() {
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

    public String lastName() {
        return lastName;
    }

    public ProfessionalTypeId professionalTypeId() {
        return professionalTypeId;
    }

    public String licenseNumber() {
        return licenseNumber;
    }

    public boolean active() {
        return active;
    }

    public CityMunicipalityId cityId() {
        return cityId;
    }

    public ContactId contactId() {
        return contactId;
    }
}
