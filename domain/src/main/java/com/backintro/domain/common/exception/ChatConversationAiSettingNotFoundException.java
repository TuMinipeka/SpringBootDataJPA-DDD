package com.backintro.domain.common.exception;

import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public class ChatConversationAiSettingNotFoundException extends RuntimeException {

    public ChatConversationAiSettingNotFoundException(ChatConversationAiSettingId id) {
        super("Chat conversation AI setting not found with id: " + id.value());
    }
}
