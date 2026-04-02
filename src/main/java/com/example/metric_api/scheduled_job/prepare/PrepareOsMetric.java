package com.example.metric_api.scheduled_job.prepare;

import java.lang.management.ManagementFactory;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.OsDto;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;

public class PrepareOsMetric {
	
	public OsDto collectOsMetrics() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		OsDto osDto = new OsDto();
		
		osDto.setOsName(osBean.getName());
		osDto.setOsVersion(osBean.getVersion());
		
		if(osDto.getOsName() == null && osDto.getOsVersion() == null) {
			throw new BaseException(ResponseType.OS_METRICS_NOT_FOUND);
		}
		return osDto;
	}

}
