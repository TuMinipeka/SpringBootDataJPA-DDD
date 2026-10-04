package com.backintro.application.sendertype.usecase;

import java.util.List;

import com.backintro.application.sendertype.dto.SenderTypeResponse;
import com.backintro.domain.sendertype.port.repository.SenderTypeRepository;

public class ListSenderTypeUseCase {

    private final SenderTypeRepository senderTypeRepository;

    public ListSenderTypeUseCase(
            SenderTypeRepository senderTypeRepository
    ) {
        this.senderTypeRepository = senderTypeRepository;
    }

    public List<SenderTypeResponse> execute() {
        return senderTypeRepository.findAll()
                .stream()
                .map(senderType ->
                        new SenderTypeResponse(
                                senderType.id().value(),
                                senderType.nameType()
                        )
                )
                .toList();
    }
}
