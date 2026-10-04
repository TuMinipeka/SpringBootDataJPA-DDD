package com.backintro.application.sendertype.usecase;

import java.time.LocalDateTime;

import com.backintro.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.backintro.domain.sendertype.event.SenderTypeDeletedEvent;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;
import com.backintro.domain.sendertype.port.repository.SenderTypeRepository;

public class DeleteSenderTypeUseCase {

    private final SenderTypeRepository senderTypeRepository;

    public DeleteSenderTypeUseCase(
            SenderTypeRepository senderTypeRepository
    ) {
        this.senderTypeRepository = senderTypeRepository;
    }

    public SenderTypeDeletedEvent execute(SenderTypeId id) {
        var senderType = senderTypeRepository.findById(id)
                .orElseThrow(() ->
                        new SenderTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        senderTypeRepository.delete(senderType);

        return new SenderTypeDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
