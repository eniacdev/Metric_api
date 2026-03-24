package com.example.metric_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.metric_api.model.SystemLogDto;
import com.example.metric_api.service.IMetricsService;

@RestController
@RequestMapping(path = "/homeserver")
public class MetricsControllerImpl implements IMetricsController{
	
	private final IMetricsService metricsService;
	
	//constructor injection.
	public MetricsControllerImpl(IMetricsService metricsService) {
		this.metricsService = metricsService;
	}

	//client schedule tetiklenmesini beklemek yerine kendi manuel olarak tetikleyebilir.
	@Override
	@GetMapping(path = "/get/metrics")
	public SystemLogDto prepareAndCreateMetrics() {
		return metricsService.prepareAndCreateMetrics();
	}

}