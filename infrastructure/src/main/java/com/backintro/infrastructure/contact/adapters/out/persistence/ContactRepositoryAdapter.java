package com.backintro.infrastructure.contact.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.contact.model.aggregate.Contact;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.contact.port.repository.ContactRepository;
import com.backintro.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;
import com.backintro.infrastructure.contact.adapters.out.persistence.mapper.ContactPersistenceMapper;
import com.backintro.infrastructure.contact.adapters.out.persistence.repository.ContactJpaRepository;

@Repository
@Transactional
public class ContactRepositoryAdapter implements ContactRepository {

    private final ContactJpaRepository repository;
    private final ContactPersistenceMapper mapper;

    public ContactRepositoryAdapter(
            ContactJpaRepository repository,
            ContactPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Contact save(Contact contact) {
        ContactJpaEntity entity = repository.findById(contact.id().value())
                .map(existing -> {
                    mapper.synchronize(contact, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(contact));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contact> findById(ContactId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Contact contact) {
        repository.deleteById(contact.id().value());
    }
}
