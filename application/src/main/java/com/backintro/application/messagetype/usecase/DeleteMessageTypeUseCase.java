package com.backintro.application.messagetype.usecase;

import java.time.LocalDateTime;

import com.backintro.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.backintro.domain.messagetype.event.MessageTypeDeletedEvent;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;

public class DeleteMessageTypeUseCase {

    private final MessageTypeRepository messageTypeRepository;

    public DeleteMessageTypeUseCase(
            MessageTypeRepository messageTypeRepository
    ) {
        this.messageTypeRepository = messageTypeRepository;
    }

    public MessageTypeDeletedEvent execute(MessageTypeId id) {
        var messageType = messageTypeRepository.findById(id)
                .orElseThrow(() ->
                        new MessageTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        messageTypeRepository.delete(messageType);

        return new MessageTypeDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
