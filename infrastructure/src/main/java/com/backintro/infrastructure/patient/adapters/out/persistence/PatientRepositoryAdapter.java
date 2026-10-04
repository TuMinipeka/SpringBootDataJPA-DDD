package com.backintro.infrastructure.patient.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.patient.model.aggregate.Patient;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patient.port.repository.PatientRepository;
import com.backintro.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;
import com.backintro.infrastructure.patient.adapters.out.persistence.mapper.PatientPersistenceMapper;
import com.backintro.infrastructure.patient.adapters.out.persistence.repository.PatientJpaRepository;

@Repository
@Transactional
public class PatientRepositoryAdapter implements PatientRepository {

    private final PatientJpaRepository repository;
    private final PatientPersistenceMapper mapper;

    public PatientRepositoryAdapter(
            PatientJpaRepository repository,
            PatientPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Patient save(Patient patient) {
        PatientJpaEntity entity = repository.findById(patient.id().value())
                .map(existing -> {
                    mapper.synchronize(patient, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(patient));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Patient> findById(PatientId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Patient> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDocumentTypeIdAndDocumentNumber(
            DocumentTypeId documentTypeId,
            String documentNumber
    ) {
        return repository.existsByDocumentTypeIdAndDocumentNumberIgnoreCase(
                documentTypeId.value(),
                documentNumber
        );
    }

    @Override
    public void delete(Patient patient) {
        repository.deleteById(patient.id().value());
    }
}
