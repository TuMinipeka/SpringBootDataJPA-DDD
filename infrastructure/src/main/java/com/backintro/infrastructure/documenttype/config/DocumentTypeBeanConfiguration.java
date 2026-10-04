package com.backintro.infrastructure.documenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.documenttype.usecase.DeleteDocumentTypeUseCase;
import com.backintro.application.documenttype.usecase.GetDocumentTypeByIdUseCase;
import com.backintro.application.documenttype.usecase.ListDocumentTypeUseCase;
import com.backintro.application.documenttype.usecase.RegisterDocumentTypeUseCase;
import com.backintro.application.documenttype.usecase.UpdateDocumentTypeUseCase;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;

@Configuration
public class DocumentTypeBeanConfiguration {

    @Bean
    RegisterDocumentTypeUseCase registerDocumentTypeUseCase(
            DocumentTypeRepository repository
    ) {
        return new RegisterDocumentTypeUseCase(repository);
    }

    @Bean
    GetDocumentTypeByIdUseCase getDocumentTypeByIdUseCase(
            DocumentTypeRepository repository
    ) {
        return new GetDocumentTypeByIdUseCase(repository);
    }

    @Bean
    ListDocumentTypeUseCase listDocumentTypeUseCase(
            DocumentTypeRepository repository
    ) {
        return new ListDocumentTypeUseCase(repository);
    }

    @Bean
    UpdateDocumentTypeUseCase updateDocumentTypeUseCase(
            DocumentTypeRepository repository
    ) {
        return new UpdateDocumentTypeUseCase(repository);
    }

    @Bean
    DeleteDocumentTypeUseCase deleteDocumentTypeUseCase(
            DocumentTypeRepository repository
    ) {
        return new DeleteDocumentTypeUseCase(repository);
    }
}
