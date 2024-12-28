package ru.otus.bot.metric.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.bot.metric.data.MetricType;
import ru.otus.bot.metric.data.MetricsDto;
import ru.otus.bot.metric.repository.MetricsRepository;
import ru.otus.bot.metric.repository.mapper.MetricsMapper;
import ru.otus.bot.metric.repository.model.Metrics;


@Service
@RequiredArgsConstructor
public class MetricsService {
    private final MetricsRepository metricsRepository;
    private final MetricsMapper metricsMapper;


    @Transactional
    public void save(Metrics metrics) {
        metricsRepository.save(processMetrics(metrics));
    }

    @Transactional(readOnly = true)
    public MetricsDto findByIdAndMetricType(String userId, MetricType metricType) {
        Metrics metrics = Optional.ofNullable(metricsRepository.findByUserIdAndMetricType(userId, metricType))
                                  .orElse(new Metrics().setValue("0")
                                                       .setDate(LocalDate.now())
                                                       .setId(userId)
                                                       .setMetricType(metricType));
        return metricsMapper.convertFromMetricsToMetricsDto(metrics);
    }


    @Transactional(readOnly = true)
    public List<Metrics> findAll() {
        return metricsRepository.findAll();
    }

    private Metrics processMetrics(Metrics metrics) {
        Metrics oldMetrics = Optional.ofNullable(metricsRepository.findByUserIdAndMetricType(metrics.getUserId(), metrics.getMetricType()))
                                     .orElse(new Metrics().setValue("0").setId(UUID.randomUUID().toString()));
        return new Metrics().setId(oldMetrics.getId())
                            .setUserId(metrics.getUserId())
                            .setMetricType(metrics.getMetricType())
                            .setDate(metrics.getDate())
                            .setValue(updateMetricsValue(metrics, oldMetrics));
    }

    private String updateMetricsValue(Metrics metrics, Metrics oldMetrics) {
        return metrics.getMetricType() == MetricType.MILEAGE
                ? metrics.getValue()
                : String.valueOf(Long.parseLong(metrics.getValue()) + Long.parseLong(oldMetrics.getValue()));
    }
}
