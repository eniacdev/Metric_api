package com.example.HomeServerAPI.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.example.HomeServerAPI.model.SystemLog;
import com.example.HomeServerAPI.model.SystemLogDto;
import com.example.HomeServerAPI.repository.IMetricRepository;
import com.example.HomeServerAPI.scheduled_job.PreapreSystemMetrics;
import com.example.HomeServerAPI.scheduled_job.PrepareJsonFile;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements IMetricsService{
	
	private final IMetricRepository metricRepository;
	private final PreapreSystemMetrics systemMetrics;
	private final PrepareJsonFile prepareJsonFile;
	

	@Override
	public SystemLogDto prepareAndCreateMetrics(){
		
		try {
		// schedule tetiklendiğinde servise (buraya) yönlendirir.
		//ayrıca client'ta manuel tetikleme yapabilir.
			 
			//metrikleri toplaması için prepareSystemMetrics'e yönlendirir.
		SystemLogDto createdMetrics = systemMetrics.preapreSystemMetrics();
	    SystemLog metrics = new SystemLog();
	    
	    BeanUtils.copyProperties(createdMetrics, metrics);
	    
	    //gelen metrikleri veritabanına kaydeder.
	    metricRepository.save(metrics);
	    
	    //sonrasında metrikleri json olarak kaydetmesi için writeJsonFile metoduna yönlendirir.
	    prepareJsonFile.writeJsonFile(createdMetrics);
	    
	    return createdMetrics;
	    
		}catch (Exception e) {
			System.out.println("metrikler toplanırken bir hata oluştu: " + e);
			return null;
		}
	}

}
