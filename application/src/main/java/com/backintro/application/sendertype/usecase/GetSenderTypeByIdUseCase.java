package com.backintro.application.sendertype.usecase;

import com.backintro.application.sendertype.dto.SenderTypeResponse;
import com.backintro.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;
import com.backintro.domain.sendertype.port.repository.SenderTypeRepository;

public class GetSenderTypeByIdUseCase {

    private final SenderTypeRepository senderTypeRepository;

    public GetSenderTypeByIdUseCase(
            SenderTypeRepository senderTypeRepository
    ) {
        this.senderTypeRepository = senderTypeRepository;
    }

    public SenderTypeResponse execute(SenderTypeId id) {
        var senderType = senderTypeRepository.findById(id)
                .orElseThrow(() ->
                        new SenderTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new SenderTypeResponse(
                senderType.id().value(),
                senderType.nameType()
        );
    }
}
