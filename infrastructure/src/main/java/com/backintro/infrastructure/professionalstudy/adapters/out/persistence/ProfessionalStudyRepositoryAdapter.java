package com.backintro.infrastructure.professionalstudy.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.backintro.domain.professionalstudy.port.repository.ProfessionalStudyRepository;
import com.backintro.domain.study.model.valueobject.StudyId;
import com.backintro.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;
import com.backintro.infrastructure.professionalstudy.adapters.out.persistence.mapper.ProfessionalStudyPersistenceMapper;
import com.backintro.infrastructure.professionalstudy.adapters.out.persistence.repository.ProfessionalStudyJpaRepository;

@Repository
@Transactional
public class ProfessionalStudyRepositoryAdapter
        implements ProfessionalStudyRepository {

    private final ProfessionalStudyJpaRepository repository;
    private final ProfessionalStudyPersistenceMapper mapper;

    public ProfessionalStudyRepositoryAdapter(
            ProfessionalStudyJpaRepository repository,
            ProfessionalStudyPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProfessionalStudy save(ProfessionalStudy professionalStudy) {
        ProfessionalStudyJpaEntity entity = repository
                .findById(professionalStudy.id().value())
                .map(existing -> {
                    mapper.synchronize(professionalStudy, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(professionalStudy));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfessionalStudy> findById(ProfessionalStudyId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfessionalStudy> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByProfessionalIdAndStudyIdAndTitle(
            ProfessionalId professionalId,
            StudyId studyId,
            String title
    ) {
        return repository.existsByProfessionalIdAndStudyIdAndTitleIgnoreCase(
                professionalId.value(),
                studyId.value(),
                title
        );
    }

    @Override
    public void delete(ProfessionalStudy professionalStudy) {
        repository.deleteById(professionalStudy.id().value());
    }
}
