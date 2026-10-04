package com.backintro.infrastructure.chatconversationaisetting.adapters.in.rest.dtos;

import java.util.UUID;

public record UpdateChatConversationAiSettingRequest(

        boolean aiEnabled,

        UUID defaultModelId

) {
}
