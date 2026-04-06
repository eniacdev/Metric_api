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
import org.springframework.test.web.servlet.MockMvc;
import com.example.metric_api.controller.MetricsControllerImpl;
import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.OsDto;
import com.example.metric_api.model.SystemLogDto;
import com.example.metric_api.model.UptimeMetricDto;
import com.example.metric_api.scheduled_job.export.PrepareJsonFile;
import com.example.metric_api.service.IMetricsService;



@WebMvcTest(MetricsControllerImpl.class)
public class MetricsControllerImplTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IMetricsService metricsService;
	
	@MockBean
	private PrepareJsonFile prepareJsonFile;
	
	SystemLogDto metric = new SystemLogDto();
	OsDto os = new OsDto();
	CpuDto cpu = new CpuDto();
	UptimeMetricDto uptime = new UptimeMetricDto();
	DiskDto disk = new DiskDto();
	
	@BeforeEach
	public void setUp() {
		
		os.setOsName("Linux");
		metric.setOs(os);
		
		cpu.setCpuCores(5);
		cpu.setProcessCpuLoad(1.5);
		cpu.setSystemAverageLoad(1.5);
		cpu.setSystemCpuLoad(1.5);
		
	}
	
	@Test
	public void prepareAndCreateMetricsTest() throws Exception{
		
		//when
		when(metricsService.prepareAndCreateMetrics()).thenReturn(metric);
		
		mockMvc.perform(get("/homeserver/get/metrics"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.os.osName").value("Linux"))
        .andDo(print());
		
		//then
		verify(metricsService).prepareAndCreateMetrics();
		
	}
	
	@Test
	public void getCpuMetricTest() throws Exception{
		
		
		
		when(metricsService.getCpuMetric()).thenReturn(cpu);
		
		mockMvc.perform(get("/homeserver/get/cpu"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.cpu.cpuCores").value(5));
		
		verify(metricsService).getCpuMetric();
		
	}
}