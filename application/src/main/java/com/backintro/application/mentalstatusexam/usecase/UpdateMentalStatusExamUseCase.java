package com.backintro.application.mentalstatusexam.usecase;

import com.backintro.application.mentalstatusexam.command.UpdateMentalStatusExamCommand;
import com.backintro.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.backintro.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import com.backintro.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class UpdateMentalStatusExamUseCase {

    private final MentalStatusExamRepository mentalStatusExamRepository;

    public UpdateMentalStatusExamUseCase(
            MentalStatusExamRepository mentalStatusExamRepository
    ) {
        this.mentalStatusExamRepository = mentalStatusExamRepository;
    }

    public MentalStatusExamResponse execute(
            UpdateMentalStatusExamCommand command
    ) {
        var mentalStatusExam = mentalStatusExamRepository.findById(command.id())
                .orElseThrow(() ->
                        new MentalStatusExamNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        mentalStatusExam.update(
                command.appearance(),
                command.behavior(),
                command.attitude(),
                command.consciousness(),
                command.orientation(),
                command.attention(),
                command.memory(),
                command.speech(),
                command.mood(),
                command.affect(),
                command.thoughtProcess(),
                command.thoughtContent(),
                command.perception(),
                command.judgment(),
                command.insight(),
                command.psychomotorActivity(),
                command.observations()
        );

        var updated = mentalStatusExamRepository.save(mentalStatusExam);

        return new MentalStatusExamResponse(
                updated.id().value(),
                updated.appearance(),
                updated.behavior(),
                updated.attitude(),
                updated.consciousness(),
                updated.orientation(),
                updated.attention(),
                updated.memory(),
                updated.speech(),
                updated.mood(),
                updated.affect(),
                updated.thoughtProcess(),
                updated.thoughtContent(),
                updated.perception(),
                updated.judgment(),
                updated.insight(),
                updated.psychomotorActivity(),
                updated.observations()
        );
    }
}
