package com.backintro.application.mentalstatusexam.usecase;

import com.backintro.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.backintro.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import com.backintro.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.backintro.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class GetMentalStatusExamByIdUseCase {

    private final MentalStatusExamRepository mentalStatusExamRepository;

    public GetMentalStatusExamByIdUseCase(
            MentalStatusExamRepository mentalStatusExamRepository
    ) {
        this.mentalStatusExamRepository = mentalStatusExamRepository;
    }

    public MentalStatusExamResponse execute(MentalStatusExamId id) {
        var mentalStatusExam = mentalStatusExamRepository.findById(id)
                .orElseThrow(() ->
                        new MentalStatusExamNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new MentalStatusExamResponse(
                mentalStatusExam.id().value(),
                mentalStatusExam.appearance(),
                mentalStatusExam.behavior(),
                mentalStatusExam.attitude(),
                mentalStatusExam.consciousness(),
                mentalStatusExam.orientation(),
                mentalStatusExam.attention(),
                mentalStatusExam.memory(),
                mentalStatusExam.speech(),
                mentalStatusExam.mood(),
                mentalStatusExam.affect(),
                mentalStatusExam.thoughtProcess(),
                mentalStatusExam.thoughtContent(),
                mentalStatusExam.perception(),
                mentalStatusExam.judgment(),
                mentalStatusExam.insight(),
                mentalStatusExam.psychomotorActivity(),
                mentalStatusExam.observations()
        );
    }
}
