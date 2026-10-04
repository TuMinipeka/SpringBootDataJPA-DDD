package com.backintro.application.messagetype.usecase;

import com.backintro.application.messagetype.command.RegisterMessageTypeCommand;
import com.backintro.application.messagetype.dto.MessageTypeResponse;
import com.backintro.domain.messagetype.model.aggregate.MessageType;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;

public class RegisterMessageTypeUseCase {

    private final MessageTypeRepository messageTypeRepository;

    public RegisterMessageTypeUseCase(
            MessageTypeRepository messageTypeRepository
    ) {
        this.messageTypeRepository = messageTypeRepository;
    }

    public MessageTypeResponse execute(
            RegisterMessageTypeCommand command
    ) {
        MessageType messageType =
                MessageType.register(command.nameType());
        MessageType saved =
                messageTypeRepository.save(messageType);

        return new MessageTypeResponse(
                saved.id().value(),
                saved.nameType()
        );
    }
}
