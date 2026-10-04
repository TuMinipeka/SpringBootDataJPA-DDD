package com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.backintro.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.backintro.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;
import com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;
import com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.mapper.MentalStatusExamPersistenceMapper;
import com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.repository.MentalStatusExamJpaRepository;

@Repository
@Transactional
public class MentalStatusExamRepositoryAdapter
        implements MentalStatusExamRepository {

    private final MentalStatusExamJpaRepository repository;
    private final MentalStatusExamPersistenceMapper mapper;

    public MentalStatusExamRepositoryAdapter(
            MentalStatusExamJpaRepository repository,
            MentalStatusExamPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MentalStatusExam save(MentalStatusExam mentalStatusExam) {
        MentalStatusExamJpaEntity entity = repository
                .findById(mentalStatusExam.id().value())
                .map(existing -> {
                    mapper.synchronize(mentalStatusExam, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(mentalStatusExam));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MentalStatusExam> findById(MentalStatusExamId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MentalStatusExam> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEncounterId(EncounterId encounterId) {
        return repository.existsByEncounterId(encounterId.value());
    }

    @Override
    public void delete(MentalStatusExam mentalStatusExam) {
        repository.deleteById(mentalStatusExam.id().value());
    }
}
