package com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.aiprovidermodel.model.aggregate.AiProviderModel;

class AiProviderModelPersistenceMapperTest {

    private final AiProviderModelPersistenceMapper mapper =
            new AiProviderModelPersistenceMapper();

    @Test
    void mapsAiProviderModelInBothDirectionsWithoutCreatingDomainEvents() {
        AiProviderModel original = AiProviderModel.register(
                "OpenAI",
                "OpenAI, L.L.C.",
                "https://openai.com"
        );

        AiProviderModel restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.nameProviderAi()).isEqualTo("OpenAI");
        assertThat(restored.razonSocial()).isEqualTo("OpenAI, L.L.C.");
        assertThat(restored.sitioWeb()).isEqualTo("https://openai.com");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
