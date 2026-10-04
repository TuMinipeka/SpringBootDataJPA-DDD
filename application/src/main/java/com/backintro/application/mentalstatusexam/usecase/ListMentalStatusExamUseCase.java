package com.backintro.application.mentalstatusexam.usecase;

import java.util.List;

import com.backintro.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.backintro.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class ListMentalStatusExamUseCase {

    private final MentalStatusExamRepository mentalStatusExamRepository;

    public ListMentalStatusExamUseCase(
            MentalStatusExamRepository mentalStatusExamRepository
    ) {
        this.mentalStatusExamRepository = mentalStatusExamRepository;
    }

    public List<MentalStatusExamResponse> execute() {
        return mentalStatusExamRepository.findAll()
                .stream()
                .map(mentalStatusExam ->
                        new MentalStatusExamResponse(
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
                        )
                )
                .toList();
    }
}
