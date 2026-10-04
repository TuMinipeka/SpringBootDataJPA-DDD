package com.backintro.infrastructure.chatparticipant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatparticipant.usecase.DeleteChatParticipantUseCase;
import com.backintro.application.chatparticipant.usecase.GetChatParticipantByIdUseCase;
import com.backintro.application.chatparticipant.usecase.ListChatParticipantUseCase;
import com.backintro.application.chatparticipant.usecase.RegisterChatParticipantUseCase;
import com.backintro.application.chatparticipant.usecase.UpdateChatParticipantUseCase;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.chatparticipant.port.repository.ChatParticipantRepository;
import com.backintro.domain.patient.port.repository.PatientRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.sendertype.port.repository.SenderTypeRepository;

@Configuration
public class ChatParticipantBeanConfiguration {

    @Bean
    RegisterChatParticipantUseCase registerChatParticipantUseCase(
            ChatParticipantRepository chatParticipantRepository,
            ChatConversationRepository chatConversationRepository,
            SenderTypeRepository senderTypeRepository,
            PatientRepository patientRepository,
            ProfessionalRepository professionalRepository
    ) {
        return new RegisterChatParticipantUseCase(
                chatParticipantRepository,
                chatConversationRepository,
                senderTypeRepository,
                patientRepository,
                professionalRepository
        );
    }

    @Bean
    GetChatParticipantByIdUseCase getChatParticipantByIdUseCase(
            ChatParticipantRepository repository
    ) {
        return new GetChatParticipantByIdUseCase(repository);
    }

    @Bean
    ListChatParticipantUseCase listChatParticipantUseCase(
            ChatParticipantRepository repository
    ) {
        return new ListChatParticipantUseCase(repository);
    }

    @Bean
    UpdateChatParticipantUseCase updateChatParticipantUseCase(
            ChatParticipantRepository chatParticipantRepository,
            SenderTypeRepository senderTypeRepository,
            PatientRepository patientRepository,
            ProfessionalRepository professionalRepository
    ) {
        return new UpdateChatParticipantUseCase(
                chatParticipantRepository,
                senderTypeRepository,
                patientRepository,
                professionalRepository
        );
    }

    @Bean
    DeleteChatParticipantUseCase deleteChatParticipantUseCase(
            ChatParticipantRepository repository
    ) {
        return new DeleteChatParticipantUseCase(repository);
    }
}
