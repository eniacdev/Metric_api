package com.example.metric_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.model.OsDto;
import com.example.metric_api.model.SystemLogDto;
import com.example.metric_api.model.UptimeMetricDto;
import com.example.metric_api.response.ApiResponse;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.scheduled_job.export.PrepareJsonFile;
import com.example.metric_api.scheduled_job.prepare.PrepareCpuMetric;
import com.example.metric_api.service.IMetricsService;

@RestController
@RequestMapping(path = "/homeserver")
public class MetricsControllerImpl implements IMetricsController{

    private final PrepareJsonFile prepareJsonFile;
	
	private final IMetricsService metricsService;
	
	//constructor injection.
	public MetricsControllerImpl(IMetricsService metricsService, PrepareJsonFile prepareJsonFile) {
		this.metricsService = metricsService;
		this.prepareJsonFile = prepareJsonFile;
	}

	//client schedule tetiklenmesini beklemek yerine kendi manuel olarak tetikleyebilir.
	@Override
	@GetMapping(path = "/get/metrics")
	public ResponseEntity<ApiResponse<SystemLogDto>> prepareAndCreateMetrics() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.prepareAndCreateMetrics());
	}

	
	@Override
	@GetMapping(path = "/get/cpu")
	public ResponseEntity<ApiResponse<CpuDto>> getCpuMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getCpuMetric());
	}

	@Override
	@GetMapping(path = "/get/uptime")
	public ResponseEntity<ApiResponse<UptimeMetricDto>> getUptimeMetric() throws Exception{
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getUptimeMetric());
	}

	@Override
	@GetMapping(path = "/get/os")
	public ResponseEntity<ApiResponse<OsDto>> getOsMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getOsMetric());
	}

	@Override
	@GetMapping(path = "/get/memory")
	public ResponseEntity<ApiResponse<MemoryDto>> getMemoryMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getMemoryMetric());
	}

	@Override
	@GetMapping(path = "/get/disk")
	public ResponseEntity<ApiResponse<DiskDto>> getDiskMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getDiskMetric());
	}

}