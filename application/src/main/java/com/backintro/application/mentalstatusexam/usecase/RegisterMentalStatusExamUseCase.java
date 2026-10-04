package com.backintro.application.mentalstatusexam.usecase;

import com.backintro.application.encounter.exception.EncounterNotFoundApplicationException;
import com.backintro.application.mentalstatusexam.command.RegisterMentalStatusExamCommand;
import com.backintro.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.backintro.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class RegisterMentalStatusExamUseCase {

    private final MentalStatusExamRepository mentalStatusExamRepository;
    private final EncounterRepository encounterRepository;

    public RegisterMentalStatusExamUseCase(
            MentalStatusExamRepository mentalStatusExamRepository,
            EncounterRepository encounterRepository
    ) {
        this.mentalStatusExamRepository = mentalStatusExamRepository;
        this.encounterRepository = encounterRepository;
    }

    public MentalStatusExamResponse execute(
            RegisterMentalStatusExamCommand command
    ) {
        encounterRepository.findById(command.encounterId())
                .orElseThrow(() ->
                        new EncounterNotFoundApplicationException(
                                command.encounterId().value().toString()
                        )
                );

        MentalStatusExam mentalStatusExam = MentalStatusExam.register(
                command.encounterId(),
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

        return toResponse(mentalStatusExamRepository.save(mentalStatusExam));
    }

    private MentalStatusExamResponse toResponse(
            MentalStatusExam mentalStatusExam
    ) {
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
