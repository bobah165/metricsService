package ru.otus.bot.metric.integration.consumer;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.bot.metric.data.Car;
import ru.otus.bot.metric.data.MetricType;
import ru.otus.bot.metric.repository.model.Metrics;
import ru.otus.bot.metric.service.MetricsService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerCarService {

  private static final String topicTelegram = "${topic.consumer.car}";
  private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";
  private final MetricsService metricsService;

  @Transactional
  @KafkaListener(topics = topicTelegram, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=ru.otus.bot.metric.data.Car"})
  public Metrics createMetric(Car car) {
    log.info("Car message consumed {}", car);

    return metricsService.save(new Metrics().setMetricType(MetricType.MILEAGE)
        .setDate(LocalDate.now())
        .setValue(String.valueOf(car.getMileage()))
        .setUserId(car.getId()));
  }
}
