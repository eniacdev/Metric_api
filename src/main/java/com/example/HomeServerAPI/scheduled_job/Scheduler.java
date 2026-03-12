package com.example.HomeServerAPI.scheduled_job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.HomeServerAPI.service.IMetricsService;

@Component
public class Scheduler {
	
	@Autowired
	private IMetricsService metricsService;
	
	/* her bir dakikada schedule tetiklenir.
	 * tetiklendiğinde servisi çağırarak metrikler toplanmaya ve hazırlanmaya başlar.
	 * en sonunda metrikler hazır olduğunda veritabanına kaydeder ve ek olarak json dosyası oluşturur.
	 * json dosyanın güncel tarihin ismiyle metrikler kaydedilir.
	 */
	@Scheduled(cron = "0 * * * * *")
	public void doSchedulerJob() {
		try {
			//logları okumak ve schedule tetiklendiğinden emin olmak için.
		System.out.println("schedule çalıştı");
		metricsService.prepareAndCreateMetrics();
		}catch (Exception e) {
			e.getMessage();
		}
	}
}
