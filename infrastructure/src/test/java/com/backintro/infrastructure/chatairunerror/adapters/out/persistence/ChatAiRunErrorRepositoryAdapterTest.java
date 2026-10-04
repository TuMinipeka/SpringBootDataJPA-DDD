package com.backintro.infrastructure.chatairunerror.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.backintro.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;
import com.backintro.infrastructure.chatairunerror.adapters.out.persistence.mapper.ChatAiRunErrorPersistenceMapper;
import com.backintro.infrastructure.chatairunerror.adapters.out.persistence.repository.ChatAiRunErrorJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatAiRunErrorRepositoryAdapterTest {

    @Mock
    private ChatAiRunErrorJpaRepository springDataRepository;

    private ChatAiRunErrorRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatAiRunErrorRepositoryAdapter(
                springDataRepository,
                new ChatAiRunErrorPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatAiRunError error = newError();
        when(springDataRepository.findById(error.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ChatAiRunErrorJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ChatAiRunError saved = adapter.save(error);

        assertThat(saved.id()).isEqualTo(error.id());
        assertThat(saved.aiRunId()).isEqualTo(error.aiRunId());
        assertThat(saved.errorMessage()).isEqualTo("Provider request failed");
        assertThat(saved.errorCode()).isEqualTo("TIMEOUT");
        assertThat(saved.providerErrorId()).isEqualTo("provider-error-123");
        verify(springDataRepository).save(any(ChatAiRunErrorJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatAiRunError error = newError();

        adapter.delete(error);

        verify(springDataRepository).deleteById(error.id().value());
    }

    private ChatAiRunError newError() {
        return ChatAiRunError.register(
                ChatAiRunId.generate(),
                "Provider request failed",
                "TIMEOUT",
                "provider-error-123"
        );
    }
}
