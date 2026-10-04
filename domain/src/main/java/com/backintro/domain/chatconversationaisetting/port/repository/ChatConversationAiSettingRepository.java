package com.backintro.domain.chatconversationaisetting.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public interface ChatConversationAiSettingRepository {

    ChatConversationAiSetting save(ChatConversationAiSetting setting);

    Optional<ChatConversationAiSetting> findById(
            ChatConversationAiSettingId id
    );

    List<ChatConversationAiSetting> findAll();

    boolean existsByConversationId(ChatConversationId conversationId);

    void delete(ChatConversationAiSetting setting);
}
