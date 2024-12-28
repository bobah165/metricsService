package ru.otus.bot.metric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.bot.metric.data.MetricType;
import ru.otus.bot.metric.repository.model.Metrics;


@Repository
public interface MetricsRepository extends JpaRepository<Metrics, String> {
    Metrics findByUserIdAndMetricType(String userId, MetricType metricType);
}
