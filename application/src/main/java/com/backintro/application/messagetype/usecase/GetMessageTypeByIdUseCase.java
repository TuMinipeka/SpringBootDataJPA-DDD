package com.backintro.application.messagetype.usecase;

import com.backintro.application.messagetype.dto.MessageTypeResponse;
import com.backintro.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;

public class GetMessageTypeByIdUseCase {

    private final MessageTypeRepository messageTypeRepository;

    public GetMessageTypeByIdUseCase(
            MessageTypeRepository messageTypeRepository
    ) {
        this.messageTypeRepository = messageTypeRepository;
    }

    public MessageTypeResponse execute(MessageTypeId id) {
        var messageType = messageTypeRepository.findById(id)
                .orElseThrow(() ->
                        new MessageTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new MessageTypeResponse(
                messageType.id().value(),
                messageType.nameType()
        );
    }
}
