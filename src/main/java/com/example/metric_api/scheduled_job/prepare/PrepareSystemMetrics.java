package com.example.metric_api.scheduled_job.prepare;

import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.metric_api.model.SystemMetricsDto;
import com.sun.management.OperatingSystemMXBean;

@Service
public class PrepareSystemMetrics {

	// gerçekleştirilen işlemleri loglara yansıtmak için kullandım.
	// importlara dikkat.
	private static final Logger log = LoggerFactory.getLogger(PrepareSystemMetrics.class);
	OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	
	public SystemMetricsDto prepareSystemMetrics() throws Exception{
		
		SystemMetricsDto metric = new SystemMetricsDto();
		
		//Preparing Metrics
		PrepareCpuMetric cpuMetric = new PrepareCpuMetric();
		PrepareDiskMetric diskMetric = new PrepareDiskMetric();
		PrepareMemoryMetric memoryMetric = new PrepareMemoryMetric();
		
		log.warn("the metrics is being preparing");
		
		metric.setCpu(cpuMetric.collectCpuMetrics());
		metric.setMemory(memoryMetric.collectMemoryMetrics());
		metric.setDisk(diskMetric.collectDiskMetrics());
		
		return metric;
	}
}
