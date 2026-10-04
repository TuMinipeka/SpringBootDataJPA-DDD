package com.backintro.application.mentalstatusexam.usecase;

import java.time.LocalDateTime;

import com.backintro.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import com.backintro.domain.mentalstatusexam.event.MentalStatusExamDeletedEvent;
import com.backintro.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.backintro.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class DeleteMentalStatusExamUseCase {

    private final MentalStatusExamRepository mentalStatusExamRepository;

    public DeleteMentalStatusExamUseCase(
            MentalStatusExamRepository mentalStatusExamRepository
    ) {
        this.mentalStatusExamRepository = mentalStatusExamRepository;
    }

    public MentalStatusExamDeletedEvent execute(MentalStatusExamId id) {
        var mentalStatusExam = mentalStatusExamRepository.findById(id)
                .orElseThrow(() ->
                        new MentalStatusExamNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        mentalStatusExamRepository.delete(mentalStatusExam);

        return new MentalStatusExamDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
