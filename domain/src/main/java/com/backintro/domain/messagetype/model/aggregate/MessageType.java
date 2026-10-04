package com.backintro.domain.messagetype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.messagetype.event.MessageTypeRegisteredEvent;
import com.backintro.domain.messagetype.event.MessageTypeUpdatedEvent;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;

public class MessageType extends AggregateRoot {

    private final MessageTypeId id;
    private String nameType;

    private MessageType(
            MessageTypeId id,
            String nameType
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameType = Objects.requireNonNull(
                nameType,
                "nameType must not be null"
        );
    }

    public static MessageType register(String nameType) {
        MessageTypeId id = MessageTypeId.generate();
        MessageType messageType =
                new MessageType(id, nameType);

        messageType.recordEvent(
                new MessageTypeRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return messageType;
    }

    public static MessageType restore(
            MessageTypeId id,
            String nameType
    ) {
        return new MessageType(id, nameType);
    }

    public void update(String nameType) {
        this.nameType = Objects.requireNonNull(
                nameType,
                "nameType must not be null"
        );

        recordEvent(
                new MessageTypeUpdatedEvent(
                        this.id,
                        this.nameType,
                        LocalDateTime.now()
                )
        );
    }

    public MessageTypeId id() {
        return id;
    }

    public String nameType() {
        return nameType;
    }
}
