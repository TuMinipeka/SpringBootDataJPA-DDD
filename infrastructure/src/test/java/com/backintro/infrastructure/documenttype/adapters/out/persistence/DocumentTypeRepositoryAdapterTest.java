package com.backintro.infrastructure.documenttype.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.documenttype.model.aggregate.DocumentType;
import com.backintro.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;
import com.backintro.infrastructure.documenttype.adapters.out.persistence.mapper.DocumentTypePersistenceMapper;
import com.backintro.infrastructure.documenttype.adapters.out.persistence.repository.DocumentTypeJpaRepository;

@ExtendWith(MockitoExtension.class)
class DocumentTypeRepositoryAdapterTest {

    @Mock
    private DocumentTypeJpaRepository springDataRepository;

    private DocumentTypeRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new DocumentTypeRepositoryAdapter(
                springDataRepository,
                new DocumentTypePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        DocumentType documentType = DocumentType.register(
                "Citizenship Card",
                "CC"
        );
        when(springDataRepository.findById(documentType.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(DocumentTypeJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        DocumentType saved = adapter.save(documentType);

        assertThat(saved.id()).isEqualTo(documentType.id());
        assertThat(saved.name()).isEqualTo("Citizenship Card");
        assertThat(saved.code()).isEqualTo("CC");
        verify(springDataRepository).save(any(DocumentTypeJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        DocumentType documentType = DocumentType.register(
                "Citizenship Card",
                "CC"
        );

        adapter.delete(documentType);

        verify(springDataRepository).deleteById(documentType.id().value());
    }
}
