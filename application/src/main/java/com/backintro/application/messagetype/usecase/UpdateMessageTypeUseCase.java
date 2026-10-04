package com.backintro.application.messagetype.usecase;

import com.backintro.application.messagetype.command.UpdateMessageTypeCommand;
import com.backintro.application.messagetype.dto.MessageTypeResponse;
import com.backintro.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;

public class UpdateMessageTypeUseCase {

    private final MessageTypeRepository messageTypeRepository;

    public UpdateMessageTypeUseCase(
            MessageTypeRepository messageTypeRepository
    ) {
        this.messageTypeRepository = messageTypeRepository;
    }

    public MessageTypeResponse execute(
            UpdateMessageTypeCommand command
    ) {
        var messageType = messageTypeRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new MessageTypeNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        messageType.update(command.nameType());

        var updated = messageTypeRepository.save(messageType);

        return new MessageTypeResponse(
                updated.id().value(),
                updated.nameType()
        );
    }
}
