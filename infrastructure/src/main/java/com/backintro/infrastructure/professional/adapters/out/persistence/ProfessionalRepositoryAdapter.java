package com.backintro.infrastructure.professional.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.professional.model.aggregate.Professional;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;
import com.backintro.infrastructure.professional.adapters.out.persistence.mapper.ProfessionalPersistenceMapper;
import com.backintro.infrastructure.professional.adapters.out.persistence.repository.ProfessionalJpaRepository;

@Repository
@Transactional
public class ProfessionalRepositoryAdapter
        implements ProfessionalRepository {

    private final ProfessionalJpaRepository repository;
    private final ProfessionalPersistenceMapper mapper;

    public ProfessionalRepositoryAdapter(
            ProfessionalJpaRepository repository,
            ProfessionalPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Professional save(Professional professional) {
        ProfessionalJpaEntity entity = repository
                .findById(professional.id().value())
                .map(existing -> {
                    mapper.synchronize(professional, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(professional));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Professional> findById(ProfessionalId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Professional> findAll() {
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
    @Transactional(readOnly = true)
    public boolean existsByLicenseNumber(String licenseNumber) {
        return repository.existsByLicenseNumberIgnoreCase(licenseNumber);
    }

    @Override
    public void delete(Professional professional) {
        repository.deleteById(professional.id().value());
    }
}
