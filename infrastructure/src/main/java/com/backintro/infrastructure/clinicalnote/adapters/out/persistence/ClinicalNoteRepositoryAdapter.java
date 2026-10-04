package com.backintro.infrastructure.clinicalnote.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.backintro.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.backintro.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import com.backintro.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;
import com.backintro.infrastructure.clinicalnote.adapters.out.persistence.mapper.ClinicalNotePersistenceMapper;
import com.backintro.infrastructure.clinicalnote.adapters.out.persistence.repository.ClinicalNoteJpaRepository;

@Repository
@Transactional
public class ClinicalNoteRepositoryAdapter
        implements ClinicalNoteRepository {

    private final ClinicalNoteJpaRepository repository;
    private final ClinicalNotePersistenceMapper mapper;

    public ClinicalNoteRepositoryAdapter(
            ClinicalNoteJpaRepository repository,
            ClinicalNotePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalNote save(ClinicalNote clinicalNote) {
        ClinicalNoteJpaEntity entity = repository
                .findById(clinicalNote.id().value())
                .map(existing -> {
                    mapper.synchronize(clinicalNote, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(clinicalNote));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClinicalNote> findById(ClinicalNoteId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClinicalNote> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ClinicalNote clinicalNote) {
        repository.deleteById(clinicalNote.id().value());
    }
}
