package com.example.metric_api.scheduled_job.prepare;

import java.lang.management.ManagementFactory;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;

public class PrepareMemoryMetric {
	
	public MemoryDto collectMemoryMetrics() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		MemoryDto memoryDto = new MemoryDto();
		
		memoryDto.setFreeMemory(osBean.getFreeMemorySize());
		memoryDto.setTotalMemory(osBean.getTotalMemorySize());
		memoryDto.setMemoryUsage(memoryDto.getTotalMemory() - memoryDto.getFreeMemory());
		
		if(memoryDto.getFreeMemory() == null && memoryDto.getMemoryUsage() == null &&
		   memoryDto.getTotalMemory() == null) {
			throw new BaseException(ResponseType.MEMORY_METRICS_NOT_FOUND);
		}
		
		return memoryDto;
	}

}
