package com.example.metric_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.model.OsDto;
import com.example.metric_api.model.SystemInfo;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.model.UptimeMetricDto;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.scheduled_job.export.PrepareJsonFile;
import com.example.metric_api.scheduled_job.prepare.PrepareCpuMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareDiskMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareMemoryMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareOsMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareSystemInfo;
import com.example.metric_api.scheduled_job.prepare.PrepareSystemMetrics;
import com.example.metric_api.scheduled_job.prepare.PrepareUptimeMetric;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements IMetricsService{
	
	private final PrepareSystemMetrics systemMetrics;
	private final PrepareJsonFile prepareJsonFile;
	private static final Logger log = LoggerFactory.getLogger(PrepareSystemMetrics.class);

	
	@Override
	public SystemMetricsDto prepareAndGetMetrics(){
		
		try {
		// schedule tetiklendiğinde servise (buraya) yönlendirir.
		//ayrıca client'ta manuel tetikleme yapabilir.	
			
		SystemMetricsDto createdMetrics = systemMetrics.prepareSystemMetrics();
	    
	    prepareJsonFile.writeJsonFile(createdMetrics);
	    
	    return createdMetrics;
	    
		}catch (Exception e) {
			log.error("Metrikler toplanırken bir hata oluştu: {}", e.getMessage());
	        throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}
	}

	@Override
	public CpuDto getCpuMetric() {
		PrepareCpuMetric cpuMetric = new PrepareCpuMetric();
		CpuDto cpuDto =  cpuMetric.collectCpuMetrics();
		return cpuDto;
	}

	@Override
	public MemoryDto getMemoryMetric() {
		PrepareMemoryMetric memoryMetric = new PrepareMemoryMetric();
		MemoryDto memoryDto = memoryMetric.collectMemoryMetrics();
		return memoryDto;
	}
	
	@Override
	public DiskDto getDiskMetric() {
		PrepareDiskMetric diskMetric = new PrepareDiskMetric();
		DiskDto diskDto = diskMetric.collectDiskMetrics();
		return diskDto;
	}

	@Override
	public SystemInfo prepareAndGetSystemInfo() throws Exception{
		PrepareSystemInfo prepareSystemInfo = new PrepareSystemInfo();
		SystemInfo preparedSystemInfo = prepareSystemInfo.collectSystemInfo();
		return preparedSystemInfo;
	}
	
	

}
