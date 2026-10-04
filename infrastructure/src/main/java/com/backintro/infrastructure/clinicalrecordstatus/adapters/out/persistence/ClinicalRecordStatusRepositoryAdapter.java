package com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;
import com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence.entity.ClinicalRecordStatusJpaEntity;
import com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence.mapper.ClinicalRecordStatusPersistenceMapper;
import com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence.repository.ClinicalRecordStatusJpaRepository;

@Repository
@Transactional
public class ClinicalRecordStatusRepositoryAdapter
        implements ClinicalRecordStatusRepository {

    private final ClinicalRecordStatusJpaRepository repository;
    private final ClinicalRecordStatusPersistenceMapper mapper;

    public ClinicalRecordStatusRepositoryAdapter(
            ClinicalRecordStatusJpaRepository repository,
            ClinicalRecordStatusPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalRecordStatus save(
            ClinicalRecordStatus clinicalRecordStatus
    ) {
        ClinicalRecordStatusJpaEntity entity = repository
                .findById(clinicalRecordStatus.id().value())
                .map(existing -> {
                    mapper.synchronize(clinicalRecordStatus, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(clinicalRecordStatus));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClinicalRecordStatus> findById(
            ClinicalRecordStatusId id
    ) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClinicalRecordStatus> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return repository.existsByCodeIgnoreCase(code);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return repository.existsByNameIgnoreCase(name);
    }

    @Override
    public void delete(ClinicalRecordStatus clinicalRecordStatus) {
        repository.deleteById(clinicalRecordStatus.id().value());
    }
}
