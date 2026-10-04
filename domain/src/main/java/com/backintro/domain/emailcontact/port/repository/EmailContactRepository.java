package com.backintro.domain.emailcontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.emailcontact.model.aggregate.EmailContact;
import com.backintro.domain.emailcontact.model.valueobject.EmailContactId;

public interface EmailContactRepository {

    EmailContact save(EmailContact emailContact);

    Optional<EmailContact> findById(EmailContactId id);

    List<EmailContact> findAll();

    boolean existsByContactIdAndEmail(ContactId contactId, String email);

    void delete(EmailContact emailContact);
}
