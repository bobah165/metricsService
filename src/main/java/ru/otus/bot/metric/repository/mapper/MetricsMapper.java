package ru.otus.bot.metric.repository.mapper;

import org.springframework.stereotype.Component;
import ru.otus.bot.metric.data.MetricsDto;
import ru.otus.bot.metric.repository.model.Metrics;

@Component
public class MetricsMapper {

    public MetricsDto convertFromMetricsToMetricsDto(Metrics metrics) {
        return new MetricsDto().setUserId(metrics.getUserId())
                               .setMetricType(metrics.getMetricType())
                               .setDate(metrics.getDate())
                               .setValue(metrics.getValue());
    }

    public Metrics convertFromMetricsDtoToMetrics(MetricsDto metricsDto) {
        return new Metrics().setUserId(metricsDto.getUserId())
                            .setMetricType(metricsDto.getMetricType())
                            .setDate(metricsDto.getDate())
                            .setValue(metricsDto.getValue());
    }
}
