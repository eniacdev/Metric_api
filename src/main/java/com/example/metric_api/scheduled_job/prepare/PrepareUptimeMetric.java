package com.example.metric_api.scheduled_job.prepare;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.metric_api.model.UptimeMetricDto;

public class PrepareUptimeMetric {
	
	public UptimeMetricDto collectUptimeMetric() throws Exception{
		
		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		long uptime = rb.getUptime();
		
		UptimeMetricDto upTimeMetricDto = new UptimeMetricDto();
		upTimeMetricDto.setOsUpTime(osUptime());
		upTimeMetricDto.setServiceUpTime(uptime);
		
		return upTimeMetricDto;
	}
	
	public Long osUptime() throws Exception{
		
		String content = Files.readString(Path.of("/proc/uptime"));
	    return (long) Double.parseDouble(content.split(" ")[0]);
		
	}
	
	public Long serviceUpTime() {

		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		Long serviceUpTime = rb.getUptime();
		
		return serviceUpTime;

	}

}
