package com.example.HomeServerAPI.controller.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.HomeServerAPI.model.OsDto;
import com.example.HomeServerAPI.model.SystemLogDto;
import com.example.HomeServerAPI.service.IMetricsService;


@SpringBootTest
@AutoConfigureMockMvc
public class MetricsControllerImplTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IMetricsService metricsService;
	
	@Test
	public void prepareAndCreateMetricsTest() throws Exception{
		SystemLogDto metric = new SystemLogDto();
		OsDto osDto = new OsDto();
		
		//given
		osDto.setOsName("Linux");
		metric.setOs(osDto);
		
		//when
		when(metricsService.prepareAndCreateMetrics()).thenReturn(metric);
		
		mockMvc.perform(get("/homeserver/api/prepare/create/metrics"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.os.osName").value("Linux"));
		
		//then
		verify(metricsService).prepareAndCreateMetrics();
		
	}
}