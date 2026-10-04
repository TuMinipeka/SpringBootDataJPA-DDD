package com.backintro.infrastructure.professionalstudy.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;

public interface ProfessionalStudyJpaRepository
        extends JpaRepository<ProfessionalStudyJpaEntity, UUID> {

    boolean existsByProfessionalIdAndStudyIdAndTitleIgnoreCase(
            UUID professionalId,
            UUID studyId,
            String title
    );
}
