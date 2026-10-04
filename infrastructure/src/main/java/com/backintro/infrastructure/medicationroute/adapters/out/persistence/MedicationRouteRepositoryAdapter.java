package com.backintro.infrastructure.medicationroute.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.medicationroute.model.aggregate.MedicationRoute;
import com.backintro.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.backintro.domain.medicationroute.port.repository.MedicationRouteRepository;
import com.backintro.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;
import com.backintro.infrastructure.medicationroute.adapters.out.persistence.mapper.MedicationRoutePersistenceMapper;
import com.backintro.infrastructure.medicationroute.adapters.out.persistence.repository.MedicationRouteJpaRepository;

@Repository
@Transactional
public class MedicationRouteRepositoryAdapter
        implements MedicationRouteRepository {

    private final MedicationRouteJpaRepository repository;
    private final MedicationRoutePersistenceMapper mapper;

    public MedicationRouteRepositoryAdapter(
            MedicationRouteJpaRepository repository,
            MedicationRoutePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MedicationRoute save(MedicationRoute medicationRoute) {
        MedicationRouteJpaEntity entity = repository
                .findById(medicationRoute.id().value())
                .map(existing -> {
                    mapper.synchronize(medicationRoute, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(medicationRoute));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MedicationRoute> findById(MedicationRouteId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicationRoute> findAll() {
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
    public void delete(MedicationRoute medicationRoute) {
        repository.deleteById(medicationRoute.id().value());
    }
}
