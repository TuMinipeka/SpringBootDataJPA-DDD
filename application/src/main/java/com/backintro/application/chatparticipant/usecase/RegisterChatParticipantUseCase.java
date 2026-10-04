package com.backintro.application.chatparticipant.usecase;

import com.backintro.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.backintro.application.chatparticipant.command.RegisterChatParticipantCommand;
import com.backintro.application.chatparticipant.dto.ChatParticipantResponse;
import com.backintro.application.patient.exception.PatientNotFoundApplicationException;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.backintro.domain.chatparticipant.port.repository.ChatParticipantRepository;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patient.port.repository.PatientRepository;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;
import com.backintro.domain.sendertype.port.repository.SenderTypeRepository;

public class RegisterChatParticipantUseCase {

    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatConversationRepository chatConversationRepository;
    private final SenderTypeRepository senderTypeRepository;
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;

    public RegisterChatParticipantUseCase(
            ChatParticipantRepository chatParticipantRepository,
            ChatConversationRepository chatConversationRepository,
            SenderTypeRepository senderTypeRepository,
            PatientRepository patientRepository,
            ProfessionalRepository professionalRepository
    ) {
        this.chatParticipantRepository = chatParticipantRepository;
        this.chatConversationRepository = chatConversationRepository;
        this.senderTypeRepository = senderTypeRepository;
        this.patientRepository = patientRepository;
        this.professionalRepository = professionalRepository;
    }

    public ChatParticipantResponse execute(
            RegisterChatParticipantCommand command
    ) {
        chatConversationRepository.findById(command.conversationId())
                .orElseThrow(() ->
                        new ChatConversationNotFoundApplicationException(
                                command.conversationId().value().toString()
                        )
                );
        validateReferences(
                command.participantTypeId(),
                command.patientId(),
                command.professionalId()
        );

        ChatParticipant chatParticipant = ChatParticipant.register(
                command.conversationId(),
                command.participantTypeId(),
                command.patientId(),
                command.professionalId()
        );

        return toResponse(chatParticipantRepository.save(chatParticipant));
    }

    private void validateReferences(
            SenderTypeId participantTypeId,
            PatientId patientId,
            ProfessionalId professionalId
    ) {
        senderTypeRepository.findById(participantTypeId)
                .orElseThrow(() ->
                        new SenderTypeNotFoundApplicationException(
                                participantTypeId.value().toString()
                        )
                );
        if (patientId != null) {
            patientRepository.findById(patientId)
                    .orElseThrow(() ->
                            new PatientNotFoundApplicationException(
                                    patientId.value().toString()
                            )
                    );
        }
        if (professionalId != null) {
            professionalRepository.findById(professionalId)
                    .orElseThrow(() ->
                            new ProfessionalNotFoundApplicationException(
                                    professionalId.value().toString()
                            )
                    );
        }
    }

    private ChatParticipantResponse toResponse(
            ChatParticipant chatParticipant
    ) {
        return new ChatParticipantResponse(
                chatParticipant.id().value(),
                chatParticipant.conversationId().value(),
                chatParticipant.participantTypeId().value(),
                chatParticipant.patientId() == null
                        ? null
                        : chatParticipant.patientId().value(),
                chatParticipant.professionalId() == null
                        ? null
                        : chatParticipant.professionalId().value()
        );
    }
}
