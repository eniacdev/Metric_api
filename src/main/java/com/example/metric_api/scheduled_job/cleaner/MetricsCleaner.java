package com.example.metric_api.scheduled_job.cleaner;

import com.example.metric_api.repository.IMetricsRepository;
import com.example.metric_api.response.ApiResponse;
import com.example.metric_api.response.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class MetricsCleaner {

    private final IMetricsRepository metricsRepository;
    private static final Logger log = LoggerFactory.getLogger(MetricsCleaner.class);

    public MetricsCleaner(IMetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }

    @Value("${metrics.retention.days}")
    private int retentionDays;

    // cleanOldMetrics()
    //@ConfigurationProperties(prefix = "shceduler.cron.clear")
    @Scheduled(cron = "${metrics.retention.cron}")
    public void cleanOldMetrics() {
        LocalDateTime thershold = LocalDateTime.now().minusDays(retentionDays);
        int deleted = metricsRepository.deleteOlderThan(thershold);
        log.info("{} kayıt silindi", deleted);
    }
}
