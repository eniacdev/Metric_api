package com.example.metric_api.scheduled_job.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.metric_api.service.IMetricsService;

@Component
public class Scheduler {
	
	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);
	private IMetricsService metricsService;

	Scheduler(IMetricsService metricsService){
		this.metricsService = metricsService;
	}

	// cron ve zone application.properties dosyasında değişirilebilir.
	@Scheduled(cron = "${scheduler.cron.expression}", zone = "${scheduler.cron.zone}")
	public void doSchedulerJob() {
		try {
		log.info("Schedule started.");
		metricsService.saveMetrics();
		}catch (Exception e) {
			log.error("Something went wrong at Scheduler, schedule job failed: " + e.getMessage());
		}
	}
}
