package ru.otus.bot.metric.data;

import java.time.LocalDate;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MetricsDto {
    private String userId;
    private MetricType metricType;
    private String value;
    private LocalDate date;
}


