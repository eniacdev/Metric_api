package com.example.HomeServerAPI.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.HomeServerAPI.model.SystemLog;
import com.example.HomeServerAPI.model.SystemLogDto;
import com.example.HomeServerAPI.repository.IMetricRepository;
import com.example.HomeServerAPI.scheduled_job.PreapreSystemMetrics;
import com.example.HomeServerAPI.scheduled_job.PrepareJsonFile;
import com.example.HomeServerAPI.service.MetricServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MetricServiceImplTest {

	@InjectMocks
	private MetricServiceImpl metricService;
	
	@Mock
	private PrepareJsonFile prepareJsonFile;
	
	@Mock
	private PreapreSystemMetrics prepareSystemMetrics;
	
	@Mock
	private IMetricRepository metricRepository;
	
	SystemLog createdMetrics = new SystemLog();
	SystemLogDto metric = new SystemLogDto();
	
	@BeforeEach
	public void setUp(){

		//test amaçlı metrikler hazırlanır.
		
		// given
		createdMetrics.setLogId(1L);
		createdMetrics.setTimeStamp(LocalDateTime.now());
		
		createdMetrics.setOsName("os-name");
		createdMetrics.setOsVersion("os-version");
		
		createdMetrics.setCpuCores(8);
		createdMetrics.setProcessCpuLoad(10);
		createdMetrics.setSystemCpuLoad(10);
		
		createdMetrics.setFreeMemory(10);
		createdMetrics.setTotalMemory(10);
		createdMetrics.setMemoryUsage(createdMetrics.getTotalMemory() - createdMetrics.getFreeMemory());
		
		createdMetrics.setFreeDisk(10);
		createdMetrics.setTotalDisk(10);
		createdMetrics.setDiskUsage(createdMetrics.getTotalDisk() - createdMetrics.getFreeDisk());
		
	}
	
	@Test
	public void prepareAndCreateMetricsTest() throws Exception{
		
		//when
		
		//eğer createdMetrics nesnesi repository'e kaydedilebiliyorsa.
		when(metricRepository.save(any(SystemLog.class))).thenReturn(createdMetrics);
		//eğer metric nesnesi prepareSytemMetircs'e gönderilip metirkler hazırlanıyorsa.
		when(prepareSystemMetrics.preapreSystemMetrics()).thenReturn(metric);
		
		metricService.prepareAndCreateMetrics();
		
		//then
		
		//createdMetrics nesnesi null olamaz
		assertNotNull(createdMetrics);
		
		//onayla
		verify(metricRepository).save(any(SystemLog.class));
		
	}
}