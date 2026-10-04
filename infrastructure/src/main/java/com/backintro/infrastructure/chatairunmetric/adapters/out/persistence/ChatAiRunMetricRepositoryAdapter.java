package com.backintro.infrastructure.chatairunmetric.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.backintro.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;
import com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;
import com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.mapper.ChatAiRunMetricPersistenceMapper;
import com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.repository.ChatAiRunMetricJpaRepository;

@Repository
@Transactional
public class ChatAiRunMetricRepositoryAdapter
        implements ChatAiRunMetricRepository {

    private final ChatAiRunMetricJpaRepository repository;
    private final ChatAiRunMetricPersistenceMapper mapper;

    public ChatAiRunMetricRepositoryAdapter(
            ChatAiRunMetricJpaRepository repository,
            ChatAiRunMetricPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRunMetric save(ChatAiRunMetric metric) {
        ChatAiRunMetricJpaEntity entity = repository
                .findById(metric.id().value())
                .map(existing -> {
                    mapper.synchronize(metric, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(metric));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatAiRunMetric> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByAiRunId(ChatAiRunId aiRunId) {
        return repository.existsByAiRunId(aiRunId.value());
    }

    @Override
    public void delete(ChatAiRunMetric metric) {
        repository.deleteById(metric.id().value());
    }
}
