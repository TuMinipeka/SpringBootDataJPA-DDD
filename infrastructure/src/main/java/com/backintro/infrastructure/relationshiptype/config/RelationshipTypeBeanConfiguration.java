package com.backintro.infrastructure.relationshiptype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.relationshiptype.usecase.DeleteRelationshipTypeUseCase;
import com.backintro.application.relationshiptype.usecase.GetRelationshipTypeByIdUseCase;
import com.backintro.application.relationshiptype.usecase.ListRelationshipTypeUseCase;
import com.backintro.application.relationshiptype.usecase.RegisterRelationshipTypeUseCase;
import com.backintro.application.relationshiptype.usecase.UpdateRelationshipTypeUseCase;
import com.backintro.domain.relationshiptype.port.repository.RelationshipTypeRepository;

@Configuration
public class RelationshipTypeBeanConfiguration {

    @Bean
    RegisterRelationshipTypeUseCase registerRelationshipTypeUseCase(
            RelationshipTypeRepository repository
    ) {
        return new RegisterRelationshipTypeUseCase(repository);
    }

    @Bean
    GetRelationshipTypeByIdUseCase getRelationshipTypeByIdUseCase(
            RelationshipTypeRepository repository
    ) {
        return new GetRelationshipTypeByIdUseCase(repository);
    }

    @Bean
    ListRelationshipTypeUseCase listRelationshipTypeUseCase(
            RelationshipTypeRepository repository
    ) {
        return new ListRelationshipTypeUseCase(repository);
    }

    @Bean
    UpdateRelationshipTypeUseCase updateRelationshipTypeUseCase(
            RelationshipTypeRepository repository
    ) {
        return new UpdateRelationshipTypeUseCase(repository);
    }

    @Bean
    DeleteRelationshipTypeUseCase deleteRelationshipTypeUseCase(
            RelationshipTypeRepository repository
    ) {
        return new DeleteRelationshipTypeUseCase(repository);
    }
}
