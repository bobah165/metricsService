package ru.otus.bot.metric.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BotController {

  private final MetricsService metricsService;

  @GetMapping(value = "${metrics.api.sub-path.metric-type}")
  public ResponseEntity<MetricsDto> getCostsById(@PathVariable("id") Long id,
                                                 @PathVariable("metricType") MetricType metricType){
    log.info("Get request from bot for id {}", id);
    return ResponseEntity.ok(metricsService.findByIdAndMetricType(String.valueOf(id), metricType));
  }

  @GetMapping(value = "${metrics.api.baseurl}")
  public ResponseEntity<List<Metrics>> getAll(){
    return ResponseEntity.ok(metricsService.findAll());
  }

  @GetMapping("/hello")
  public ResponseEntity hello() {
    log.info("Get request from bot for id {}");
    return ResponseEntity.ok("hello from metric");
  }
}
