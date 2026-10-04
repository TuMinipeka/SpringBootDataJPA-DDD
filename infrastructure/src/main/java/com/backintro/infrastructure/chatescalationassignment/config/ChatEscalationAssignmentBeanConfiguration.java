package com.backintro.infrastructure.chatescalationassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatescalationassignment.usecase.DeleteChatEscalationAssignmentUseCase;
import com.backintro.application.chatescalationassignment.usecase.GetChatEscalationAssignmentByIdUseCase;
import com.backintro.application.chatescalationassignment.usecase.ListChatEscalationAssignmentUseCase;
import com.backintro.application.chatescalationassignment.usecase.RegisterChatEscalationAssignmentUseCase;
import com.backintro.application.chatescalationassignment.usecase.UpdateChatEscalationAssignmentUseCase;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.backintro.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

@Configuration
public class ChatEscalationAssignmentBeanConfiguration {

    @Bean
    RegisterChatEscalationAssignmentUseCase
            registerChatEscalationAssignmentUseCase(
                    ChatEscalationAssignmentRepository assignmentRepository,
                    ChatEscalationRepository escalationRepository,
                    ProfessionalRepository professionalRepository
            ) {
        return new RegisterChatEscalationAssignmentUseCase(
                assignmentRepository,
                escalationRepository,
                professionalRepository
        );
    }

    @Bean
    GetChatEscalationAssignmentByIdUseCase
            getChatEscalationAssignmentByIdUseCase(
                    ChatEscalationAssignmentRepository repository
            ) {
        return new GetChatEscalationAssignmentByIdUseCase(repository);
    }

    @Bean
    ListChatEscalationAssignmentUseCase
            listChatEscalationAssignmentUseCase(
                    ChatEscalationAssignmentRepository repository
            ) {
        return new ListChatEscalationAssignmentUseCase(repository);
    }

    @Bean
    UpdateChatEscalationAssignmentUseCase
            updateChatEscalationAssignmentUseCase(
                    ChatEscalationAssignmentRepository assignmentRepository,
                    ProfessionalRepository professionalRepository
            ) {
        return new UpdateChatEscalationAssignmentUseCase(
                assignmentRepository,
                professionalRepository
        );
    }

    @Bean
    DeleteChatEscalationAssignmentUseCase
            deleteChatEscalationAssignmentUseCase(
                    ChatEscalationAssignmentRepository repository
            ) {
        return new DeleteChatEscalationAssignmentUseCase(repository);
    }
}
