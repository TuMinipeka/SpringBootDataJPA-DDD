package com.backintro.domain.chatmessage.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.chatmessage.model.aggregate.ChatMessage;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;

public interface ChatMessageRepository {

    ChatMessage save(ChatMessage chatMessage);

    Optional<ChatMessage> findById(ChatMessageId id);

    List<ChatMessage> findAll();

    void delete(ChatMessage chatMessage);
}
