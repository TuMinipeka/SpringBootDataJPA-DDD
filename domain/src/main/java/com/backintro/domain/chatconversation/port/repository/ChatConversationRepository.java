package com.backintro.domain.chatconversation.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.chatconversation.model.aggregate.ChatConversation;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;

public interface ChatConversationRepository {

    ChatConversation save(ChatConversation chatConversation);

    Optional<ChatConversation> findById(ChatConversationId id);

    List<ChatConversation> findAll();

    void delete(ChatConversation chatConversation);
}
