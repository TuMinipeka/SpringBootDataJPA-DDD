package com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.backintro.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;

@Component
public class MentalStatusExamPersistenceMapper {

    public MentalStatusExam toDomain(MentalStatusExamJpaEntity entity) {
        return MentalStatusExam.restore(
                new MentalStatusExamId(entity.getId()),
                new EncounterId(entity.getEncounterId()),
                entity.getAppearance(),
                entity.getBehavior(),
                entity.getAttitude(),
                entity.getConsciousness(),
                entity.getOrientation(),
                entity.getAttention(),
                entity.getMemory(),
                entity.getSpeech(),
                entity.getMood(),
                entity.getAffect(),
                entity.getThoughtProcess(),
                entity.getThoughtContent(),
                entity.getPerception(),
                entity.getJudgment(),
                entity.getInsight(),
                entity.getPsychomotorActivity(),
                entity.getObservations()
        );
    }

    public MentalStatusExamJpaEntity toNewEntity(
            MentalStatusExam mentalStatusExam
    ) {
        return new MentalStatusExamJpaEntity(
                mentalStatusExam.id().value(),
                mentalStatusExam.encounterId().value(),
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

    public void synchronize(
            MentalStatusExam mentalStatusExam,
            MentalStatusExamJpaEntity entity
    ) {
        entity.synchronize(
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
