package com.backintro.domain.patientcontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientcontact.model.aggregate.PatientContact;
import com.backintro.domain.patientcontact.model.valueobject.PatientContactId;

public interface PatientContactRepository {

    PatientContact save(PatientContact patientContact);

    Optional<PatientContact> findById(PatientContactId id);

    List<PatientContact> findAll();

    boolean existsByPatientIdAndContactId(
            PatientId patientId,
            ContactId contactId
    );

    void delete(PatientContact patientContact);
}
