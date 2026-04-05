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
import com.example.metric_api.model.SystemLogDto;
import com.example.metric_api.model.UptimeMetricDto;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.scheduled_job.export.PrepareJsonFile;
import com.example.metric_api.scheduled_job.prepare.PrepareCpuMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareDiskMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareMemoryMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareOsMetric;
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
	public SystemLogDto prepareAndCreateMetrics(){
		
		try {
		// schedule tetiklendiğinde servise (buraya) yönlendirir.
		//ayrıca client'ta manuel tetikleme yapabilir.	
			
		SystemLogDto createdMetrics = systemMetrics.prepareSystemMetrics();
	    
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
	public UptimeMetricDto getUptimeMetric() throws Exception{
		PrepareUptimeMetric uptimeMetric = new PrepareUptimeMetric();
		UptimeMetricDto uptimeDto = uptimeMetric.collectUptimeMetric();
		return uptimeDto;
	}

	@Override
	public OsDto getOsMetric() {
		PrepareOsMetric osMetric = new PrepareOsMetric();
		OsDto osDto = osMetric.collectOsMetrics();
		return osDto;
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
	
	

}
