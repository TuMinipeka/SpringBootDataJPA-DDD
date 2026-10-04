package com.backintro.infrastructure.clinicalrecord.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;
import com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.mapper.ClinicalRecordPersistenceMapper;
import com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.repository.ClinicalRecordJpaRepository;

@Repository
@Transactional
public class ClinicalRecordRepositoryAdapter
        implements ClinicalRecordRepository {

    private final ClinicalRecordJpaRepository repository;
    private final ClinicalRecordPersistenceMapper mapper;

    public ClinicalRecordRepositoryAdapter(
            ClinicalRecordJpaRepository repository,
            ClinicalRecordPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalRecord save(ClinicalRecord clinicalRecord) {
        ClinicalRecordJpaEntity entity = repository
                .findById(clinicalRecord.id().value())
                .map(existing -> {
                    mapper.synchronize(clinicalRecord, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(clinicalRecord));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClinicalRecord> findById(ClinicalRecordId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClinicalRecord> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByRecordNumber(String recordNumber) {
        return repository.existsByRecordNumberIgnoreCase(recordNumber);
    }

    @Override
    public void delete(ClinicalRecord clinicalRecord) {
        repository.deleteById(clinicalRecord.id().value());
    }
}
