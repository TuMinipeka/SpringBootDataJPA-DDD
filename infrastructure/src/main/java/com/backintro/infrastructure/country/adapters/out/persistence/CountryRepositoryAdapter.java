package com.backintro.infrastructure.country.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.country.model.aggregate.Country;
import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.country.port.repository.CountryRepository;
import com.backintro.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;
import com.backintro.infrastructure.country.adapters.out.persistence.mapper.CountryPersistenceMapper;
import com.backintro.infrastructure.country.adapters.out.persistence.repository.CountryJpaRepository;

@Repository
@Transactional
public class CountryRepositoryAdapter implements CountryRepository {

    private final CountryJpaRepository repository;
    private final CountryPersistenceMapper mapper;

    public CountryRepositoryAdapter(
            CountryJpaRepository repository,
            CountryPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Country save(Country country) {
        CountryJpaEntity entity = repository.findById(country.id().value())
                .map(existing -> {
                    mapper.synchronize(country, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(country));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Country> findById(CountryId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return repository.existsByCodeIgnoreCase(code);
    }

    @Override
    public void delete(Country country) {
        repository.deleteById(country.id().value());
    }
}
