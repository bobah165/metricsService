package ru.otus.bot.metric.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.bot.metric.data.MetricType;
import ru.otus.bot.metric.data.MetricsDto;
import ru.otus.bot.metric.repository.model.Metrics;
import ru.otus.bot.metric.service.MetricsService;

@RestController
@RequiredArgsConstructor
public class BotController {

  private final MetricsService metricsService;

  @GetMapping(value = "${metrics.api.sub-path.metric-type}")
  public ResponseEntity<MetricsDto> getCostsById(@PathVariable("id") Long id,
                                                 @PathVariable("metricType") MetricType metricType){
    return ResponseEntity.ok(metricsService.findByIdAndMetricType(String.valueOf(id), metricType));
  }

  @GetMapping(value = "${metrics.api.baseurl}")
  public ResponseEntity<List<Metrics>> getAll(){
    return ResponseEntity.ok(metricsService.findAll());
  }
}
