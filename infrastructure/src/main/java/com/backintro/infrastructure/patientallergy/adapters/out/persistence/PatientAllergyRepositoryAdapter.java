package com.backintro.infrastructure.patientallergy.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.patientallergy.model.aggregate.PatientAllergy;
import com.backintro.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.backintro.domain.patientallergy.port.repository.PatientAllergyRepository;
import com.backintro.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;
import com.backintro.infrastructure.patientallergy.adapters.out.persistence.mapper.PatientAllergyPersistenceMapper;
import com.backintro.infrastructure.patientallergy.adapters.out.persistence.repository.PatientAllergyJpaRepository;

@Repository
@Transactional
public class PatientAllergyRepositoryAdapter
        implements PatientAllergyRepository {

    private final PatientAllergyJpaRepository repository;
    private final PatientAllergyPersistenceMapper mapper;

    public PatientAllergyRepositoryAdapter(
            PatientAllergyJpaRepository repository,
            PatientAllergyPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PatientAllergy save(PatientAllergy allergy) {
        PatientAllergyJpaEntity entity = repository
                .findById(allergy.id().value())
                .map(existing -> {
                    mapper.synchronize(allergy, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(allergy));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientAllergy> findById(PatientAllergyId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientAllergy> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(PatientAllergy allergy) {
        repository.deleteById(allergy.id().value());
    }
}
