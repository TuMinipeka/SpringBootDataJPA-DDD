package com.backintro.infrastructure.emailcontact.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.emailcontact.model.aggregate.EmailContact;
import com.backintro.domain.emailcontact.model.valueobject.EmailContactId;
import com.backintro.domain.emailcontact.port.repository.EmailContactRepository;
import com.backintro.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;
import com.backintro.infrastructure.emailcontact.adapters.out.persistence.mapper.EmailContactPersistenceMapper;
import com.backintro.infrastructure.emailcontact.adapters.out.persistence.repository.EmailContactJpaRepository;

@Repository
@Transactional
public class EmailContactRepositoryAdapter
        implements EmailContactRepository {

    private final EmailContactJpaRepository repository;
    private final EmailContactPersistenceMapper mapper;

    public EmailContactRepositoryAdapter(
            EmailContactJpaRepository repository,
            EmailContactPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmailContact save(EmailContact emailContact) {
        EmailContactJpaEntity entity = repository
                .findById(emailContact.id().value())
                .map(existing -> {
                    mapper.synchronize(emailContact, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(emailContact));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmailContact> findById(EmailContactId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmailContact> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByContactIdAndEmail(
            ContactId contactId,
            String email
    ) {
        return repository.existsByContactIdAndEmailIgnoreCase(
                contactId.value(),
                email
        );
    }

    @Override
    public void delete(EmailContact emailContact) {
        repository.deleteById(emailContact.id().value());
    }
}
