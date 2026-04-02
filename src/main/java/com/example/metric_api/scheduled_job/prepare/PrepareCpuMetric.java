package com.example.metric_api.scheduled_job.prepare;

import java.lang.management.ManagementFactory;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.CpuDto;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;

public class PrepareCpuMetric {
	
	public CpuDto collectCpuMetrics() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		CpuDto cpuDto = new CpuDto();
		
		cpuDto.setCpuCores(osBean.getAvailableProcessors());
		cpuDto.setProcessCpuLoad(osBean.getProcessCpuLoad() * 100);
		cpuDto.setSystemCpuLoad(osBean.getSystemCpuLoad() * 100);
		cpuDto.setSystemAverageLoad(osBean.getSystemLoadAverage());
		
		if(cpuDto.getCpuCores() == null && cpuDto.getProcessCpuLoad() == null &&
		   cpuDto.getSystemAverageLoad() == null && cpuDto.getSystemCpuLoad() == null) {
			throw new BaseException(ResponseType.CPU_METRICS_NOT_FOUND);
		}
		return cpuDto;
	}

}
