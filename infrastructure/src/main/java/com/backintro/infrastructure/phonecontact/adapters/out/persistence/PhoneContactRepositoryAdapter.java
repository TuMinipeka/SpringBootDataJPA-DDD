package com.backintro.infrastructure.phonecontact.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.phonecontact.model.aggregate.PhoneContact;
import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;
import com.backintro.domain.phonecontact.port.repository.PhoneContactRepository;
import com.backintro.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;
import com.backintro.infrastructure.phonecontact.adapters.out.persistence.mapper.PhoneContactPersistenceMapper;
import com.backintro.infrastructure.phonecontact.adapters.out.persistence.repository.PhoneContactJpaRepository;

@Repository
@Transactional
public class PhoneContactRepositoryAdapter
        implements PhoneContactRepository {

    private final PhoneContactJpaRepository repository;
    private final PhoneContactPersistenceMapper mapper;

    public PhoneContactRepositoryAdapter(
            PhoneContactJpaRepository repository,
            PhoneContactPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PhoneContact save(PhoneContact phoneContact) {
        PhoneContactJpaEntity entity = repository
                .findById(phoneContact.id().value())
                .map(existing -> {
                    mapper.synchronize(phoneContact, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(phoneContact));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PhoneContact> findById(PhoneContactId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhoneContact> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByContactIdAndPhone(
            ContactId contactId,
            String phone
    ) {
        return repository.existsByContactIdAndPhone(
                contactId.value(),
                phone
        );
    }

    @Override
    public void delete(PhoneContact phoneContact) {
        repository.deleteById(phoneContact.id().value());
    }
}
