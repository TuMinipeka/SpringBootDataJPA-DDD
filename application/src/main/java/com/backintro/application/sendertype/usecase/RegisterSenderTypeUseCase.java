package com.backintro.application.sendertype.usecase;

import com.backintro.application.sendertype.command.RegisterSenderTypeCommand;
import com.backintro.application.sendertype.dto.SenderTypeResponse;
import com.backintro.domain.sendertype.model.aggregate.SenderType;
import com.backintro.domain.sendertype.port.repository.SenderTypeRepository;

public class RegisterSenderTypeUseCase {

    private final SenderTypeRepository senderTypeRepository;

    public RegisterSenderTypeUseCase(
            SenderTypeRepository senderTypeRepository
    ) {
        this.senderTypeRepository = senderTypeRepository;
    }

    public SenderTypeResponse execute(
            RegisterSenderTypeCommand command
    ) {
        SenderType senderType =
                SenderType.register(command.nameType());
        SenderType saved =
                senderTypeRepository.save(senderType);

        return new SenderTypeResponse(
                saved.id().value(),
                saved.nameType()
        );
    }
}
