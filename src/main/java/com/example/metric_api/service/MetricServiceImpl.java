package com.example.metric_api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.metric_api.model.SystemLog;
import com.example.metric_api.model.SystemLogDto;
import com.example.metric_api.repository.IMetricRepository;
import com.example.metric_api.scheduled_job.PrepareJsonFile;
import com.example.metric_api.scheduled_job.PrepareSystemMetrics;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements IMetricsService{
	
	private final IMetricRepository metricRepository;
	private final PrepareSystemMetrics systemMetrics;
	private final PrepareJsonFile prepareJsonFile;
	

	@Override
	public SystemLogDto prepareAndCreateMetrics(){
		
		try {
		// schedule tetiklendiğinde servise (buraya) yönlendirir.
		//ayrıca client'ta manuel tetikleme yapabilir.
			 
		SystemLogDto createdMetrics = systemMetrics.prepareSystemMetrics();
	    SystemLog metrics = new SystemLog();
	    
	    BeanUtils.copyProperties(createdMetrics, metrics);
	    
	    metricRepository.save(metrics);
	    
	    prepareJsonFile.writeJsonFile(createdMetrics);
	    
	    return createdMetrics;
	    
		}catch (Exception e) {
			System.out.println("metrikler toplanırken bir hata oluştu: " + e);
			return null;
		}
	}

}
