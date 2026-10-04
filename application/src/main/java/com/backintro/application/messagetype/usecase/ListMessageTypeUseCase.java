package com.backintro.application.messagetype.usecase;

import java.util.List;

import com.backintro.application.messagetype.dto.MessageTypeResponse;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;

public class ListMessageTypeUseCase {

    private final MessageTypeRepository messageTypeRepository;

    public ListMessageTypeUseCase(
            MessageTypeRepository messageTypeRepository
    ) {
        this.messageTypeRepository = messageTypeRepository;
    }

    public List<MessageTypeResponse> execute() {
        return messageTypeRepository.findAll()
                .stream()
                .map(messageType ->
                        new MessageTypeResponse(
                                messageType.id().value(),
                                messageType.nameType()
                        )
                )
                .toList();
    }
}
