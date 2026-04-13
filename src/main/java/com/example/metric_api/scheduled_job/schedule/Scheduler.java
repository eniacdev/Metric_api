package com.example.metric_api.scheduled_job.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.metric_api.service.IMetricsService;

 

@Component
public class Scheduler {
	
	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	private IMetricsService metricsService;
	
	/* her bir dakikada schedule tetiklenir.
	 * tetiklendiğinde servisi çağırarak metrikler toplanmaya ve hazırlanmaya başlar.
	 * en sonunda metrikler hazır olduğunda veritabanına kaydeder ve ek olarak json dosyası oluşturur.
	 * json dosyanın güncel tarihin ismiyle metrikler kaydedilir.
	 */
	@Scheduled(cron = "0 8 12 * * *", zone = "Europe/Istanbul")
	public void doSchedulerJob() {
		try {
		log.warn("Schedule started.");
		metricsService.prepareAndSaveMetrics();
		}catch (Exception e) {
			e.getMessage();
		}
	}
}
