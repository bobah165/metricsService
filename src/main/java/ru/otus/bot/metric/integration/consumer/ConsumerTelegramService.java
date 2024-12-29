package ru.otus.bot.metric.integration.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.bot.metric.repository.model.Metrics;
import ru.otus.bot.metric.service.MetricsService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerTelegramService {

  private static final String topicTelegram = "${topic.consumer.telegram}";
  private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";
  private final MetricsService metricsService;

  @Transactional
  @KafkaListener(topics = topicTelegram, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=ru.otus.bot.metric.repository.model.Metrics"})
  public Metrics createMetric(Metrics metrics) {
    log.info("Metrics message consumed {}", metrics);
    metricsService.save(metrics);
    return metrics;
  }
}
