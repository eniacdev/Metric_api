package com.example.metric_api.scheduled_job.prepare;

import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.metric_api.model.SystemLogDto;
import com.sun.management.OperatingSystemMXBean;

@Service
public class PrepareSystemMetrics {

	// gerçekleştirilen işlemleri loglara yansıtmak için kullandım.
	// importlara dikkat.
	private static final Logger log = LoggerFactory.getLogger(PrepareSystemMetrics.class);
	OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	
	public SystemLogDto prepareSystemMetrics() throws Exception{
		
		SystemLogDto metric = new SystemLogDto();
		
		//Preparing Metrics
		PrepareCpuMetric cpuMetric = new PrepareCpuMetric();
		PrepareOsMetric osMetric = new PrepareOsMetric();
		PrepareDiskMetric diskMetric = new PrepareDiskMetric();
		PrepareMemoryMetric memoryMetric = new PrepareMemoryMetric();
		PrepareUptimeMetric uptimeMetric = new PrepareUptimeMetric();
		PrepareHostnameMetric hostnameMetric = new PrepareHostnameMetric();
		
		log.warn("the metrics is being preparing");
		
		metric.setOs(osMetric.collectOsMetrics());
		metric.setCpu(cpuMetric.collectCpuMetrics());
		metric.setMemory(memoryMetric.collectMemoryMetrics());
		metric.setDisk(diskMetric.collectDiskMetrics());
		metric.setTimeStamp(LocalDateTime.now());
		metric.setHostName(hostnameMetric.getHostname());
		metric.setUpTime(uptimeMetric.collectUptimeMetric());
		
		return metric;
	}
}
