package com.example.HomeServerAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.HomeServerAPI.model.SystemLogDto;
import com.example.HomeServerAPI.service.IMetricsService;

@RestController
@RequestMapping(path = "/homeserver/api")
public class MetricsControllerImpl implements IMetricsController{
	
	private final IMetricsService metricsService;
	
	//constructor injection.
	public MetricsControllerImpl(IMetricsService metricsService) {
		this.metricsService = metricsService;
	}

	//client schedule tetiklenmesini beklemek yerine kendi manuel olarak tetikleyebilir.
	@Override
	@GetMapping(path = "/prepare/create/metrics")
	public SystemLogDto prepareAndCreateMetrics() {
		return metricsService.prepareAndCreateMetrics();
	}

}