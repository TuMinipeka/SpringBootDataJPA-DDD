package com.backintro.application.sendertype.usecase;

import com.backintro.application.sendertype.command.UpdateSenderTypeCommand;
import com.backintro.application.sendertype.dto.SenderTypeResponse;
import com.backintro.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.backintro.domain.sendertype.port.repository.SenderTypeRepository;

public class UpdateSenderTypeUseCase {

    private final SenderTypeRepository senderTypeRepository;

    public UpdateSenderTypeUseCase(
            SenderTypeRepository senderTypeRepository
    ) {
        this.senderTypeRepository = senderTypeRepository;
    }

    public SenderTypeResponse execute(
            UpdateSenderTypeCommand command
    ) {
        var senderType = senderTypeRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new SenderTypeNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        senderType.update(command.nameType());

        var updated = senderTypeRepository.save(senderType);

        return new SenderTypeResponse(
                updated.id().value(),
                updated.nameType()
        );
    }
}
