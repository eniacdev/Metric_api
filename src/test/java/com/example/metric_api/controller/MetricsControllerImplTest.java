package com.example.metric_api.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.metric_api.controller.MetricsControllerImpl;
import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.model.OsDto;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.model.UptimeMetricDto;
import com.example.metric_api.scheduled_job.export.PrepareJsonFile;
import com.example.metric_api.service.IMetricsService;
import com.example.metric_api.service.MetricServiceImpl;



@WebMvcTest(MetricsControllerImpl.class)
public class MetricsControllerImplTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private IMetricsService metricsService;
	
	@MockitoBean
	private PrepareJsonFile prepareJsonFile;
	
	SystemMetricsDto metric = new SystemMetricsDto();
	OsDto os = new OsDto();
	CpuDto cpu = new CpuDto();
	UptimeMetricDto uptime = new UptimeMetricDto();
	DiskDto disk = new DiskDto();
	MemoryDto memory = new MemoryDto();
	
	@BeforeEach
	public void setUp() {
		
		os.setOsName("Linux");
		os.setOsVersion("Linux-version");
		
		cpu.setCpuCores(2);
		cpu.setProcessCpuLoad(1.5);
		cpu.setSystemAverageLoad(1.5);
		cpu.setSystemCpuLoad(1.5);
		
		memory.setFreeMemory(10L);
		memory.setTotalMemory(15L);
		memory.setMemoryUsage(memory.getTotalMemory() - memory.getFreeMemory());
		
		disk.setFreeDisk(10L);
		disk.setTotalDisk(10L);
		disk.setDiskUsage(disk.getTotalDisk() - disk.getFreeDisk());
		
		metric.setCpu(cpu);
		metric.setDisk(disk);
		metric.setMemory(memory);
	}
	
	@Test
	public void prepareAndCreateMetricsTest() throws Exception{
		
		//when
		when(metricsService.prepareAndSaveMetrics()).thenReturn(metric);
		
		mockMvc.perform(post("/homeserver/metrics/collect"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.cpu.cpuCores").value(2))
        .andDo(print());
		
		//then
		verify(metricsService).prepareAndSaveMetrics();
		
	}
	
	@Test
	public void getCpuMetricTest() throws Exception{
		
		when(metricsService.getCpuMetric()).thenReturn(cpu);
		
		mockMvc.perform(get("/homeserver/metrics/cpu"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.cpuCores").value(2))
		.andDo(print());
		
		verify(metricsService).getCpuMetric();
		
	}
	
	@Test
	public void getMemoryMetricTest() throws Exception{
		
		when(metricsService.getMemoryMetric()).thenReturn(memory);
		
		mockMvc.perform(get("/homeserver/metrics/memory"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.totalMemory").value(15L))
		.andDo(print());
		
		verify(metricsService).getMemoryMetric();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}