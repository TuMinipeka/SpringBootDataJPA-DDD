package com.backintro.application.chatconversationaisetting.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import com.backintro.domain.chatconversationaisetting.event.ChatConversationAiSettingDeletedEvent;
import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.backintro.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class DeleteChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository chatConversationAiSettingRepository;

    public DeleteChatConversationAiSettingUseCase(ChatConversationAiSettingRepository chatConversationAiSettingRepository) {
        this.chatConversationAiSettingRepository = chatConversationAiSettingRepository;
    }

    public ChatConversationAiSettingDeletedEvent execute(ChatConversationAiSettingId id) {
        var chatConversationAiSetting = chatConversationAiSettingRepository.findById(id)
                .orElseThrow(() ->
                        new ChatConversationAiSettingNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        chatConversationAiSettingRepository.delete(chatConversationAiSetting);

        return new ChatConversationAiSettingDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
