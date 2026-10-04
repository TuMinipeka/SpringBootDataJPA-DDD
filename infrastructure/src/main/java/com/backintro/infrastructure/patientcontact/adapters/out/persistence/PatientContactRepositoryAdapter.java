package com.backintro.infrastructure.patientcontact.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientcontact.model.aggregate.PatientContact;
import com.backintro.domain.patientcontact.model.valueobject.PatientContactId;
import com.backintro.domain.patientcontact.port.repository.PatientContactRepository;
import com.backintro.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;
import com.backintro.infrastructure.patientcontact.adapters.out.persistence.mapper.PatientContactPersistenceMapper;
import com.backintro.infrastructure.patientcontact.adapters.out.persistence.repository.PatientContactJpaRepository;

@Repository
@Transactional
public class PatientContactRepositoryAdapter
        implements PatientContactRepository {

    private final PatientContactJpaRepository repository;
    private final PatientContactPersistenceMapper mapper;

    public PatientContactRepositoryAdapter(
            PatientContactJpaRepository repository,
            PatientContactPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PatientContact save(PatientContact patientContact) {
        PatientContactJpaEntity entity = repository
                .findById(patientContact.id().value())
                .map(existing -> {
                    mapper.synchronize(patientContact, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(patientContact));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientContact> findById(PatientContactId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientContact> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByPatientIdAndContactId(
            PatientId patientId,
            ContactId contactId
    ) {
        return repository.existsByPatientIdAndContactId(
                patientId.value(),
                contactId.value()
        );
    }

    @Override
    public void delete(PatientContact patientContact) {
        repository.deleteById(patientContact.id().value());
    }
}
