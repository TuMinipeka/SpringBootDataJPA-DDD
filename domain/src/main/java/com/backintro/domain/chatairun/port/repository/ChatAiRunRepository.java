package com.backintro.domain.chatairun.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.chatairun.model.aggregate.ChatAiRun;
import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;

public interface ChatAiRunRepository {

    ChatAiRun save(ChatAiRun chatAiRun);

    Optional<ChatAiRun> findById(ChatAiRunId id);

    List<ChatAiRun> findAll();

    void delete(ChatAiRun chatAiRun);
}
