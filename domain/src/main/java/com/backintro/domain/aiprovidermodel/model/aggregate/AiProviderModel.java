package com.backintro.domain.aiprovidermodel.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.aiprovidermodel.event.AiProviderModelRegisteredEvent;
import com.backintro.domain.aiprovidermodel.event.AiProviderModelUpdatedEvent;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;

public class AiProviderModel extends AggregateRoot {

    private final AiProviderModelId id;
    private String nameProviderAi;
    private String razonSocial;
    private String sitioWeb;
    private boolean active;

    private AiProviderModel(
            AiProviderModelId id,
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameProviderAi = Objects.requireNonNull(
                nameProviderAi,
                "nameProviderAi must not be null"
        );
        this.razonSocial = razonSocial;
        this.sitioWeb = sitioWeb;
        this.active = active;
    }

    public static AiProviderModel register(
            String nameProviderAi,
            String razonSocial,
            String sitioWeb
    ) {
        AiProviderModelId id = AiProviderModelId.generate();
        AiProviderModel aiProviderModel =
                new AiProviderModel(
                        id,
                        nameProviderAi,
                        razonSocial,
                        sitioWeb,
                        true
                );

        aiProviderModel.recordEvent(
                new AiProviderModelRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return aiProviderModel;
    }

    public static AiProviderModel restore(
            AiProviderModelId id,
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean active
    ) {
        return new AiProviderModel(
                id,
                nameProviderAi,
                razonSocial,
                sitioWeb,
                active
        );
    }

    public void update(
            String nameProviderAi,
            String razonSocial,
            String sitioWeb
    ) {
        this.nameProviderAi = Objects.requireNonNull(
                nameProviderAi,
                "nameProviderAi must not be null"
        );
        this.razonSocial = razonSocial;
        this.sitioWeb = sitioWeb;

        recordEvent(
                new AiProviderModelUpdatedEvent(
                        this.id,
                        this.nameProviderAi,
                        this.razonSocial,
                        this.sitioWeb,
                        LocalDateTime.now()
                )
        );
    }

    public AiProviderModelId id() {
        return id;
    }

    public String nameProviderAi() {
        return nameProviderAi;
    }

    public String razonSocial() {
        return razonSocial;
    }

    public String sitioWeb() {
        return sitioWeb;
    }

    public boolean active() {
        return active;
    }
}
