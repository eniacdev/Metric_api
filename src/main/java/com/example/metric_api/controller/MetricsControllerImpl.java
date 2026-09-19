package com.example.metric_api.controller;

import com.example.metric_api.dto.*;
import com.example.metric_api.model.*;
import com.example.metric_api.repository.IMetricsRepository;
import com.example.metric_api.scheduled_job.cleaner.MetricsCleaner;
import com.example.metric_api.validator.MetricsValidator;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.metric_api.response.ApiResponse;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.service.IMetricsService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/metrics")
public class MetricsControllerImpl implements IMetricsController{

	private final IMetricsService metricsService;
	private final IMetricsRepository metricsRepository;
	private final MetricsCleaner metricsCleaner;

	public MetricsControllerImpl(IMetricsService metricsService, IMetricsRepository metricsRepository, MetricsCleaner metricsCleaner) {
		this.metricsService = metricsService;
		this.metricsRepository = metricsRepository;
		this.metricsCleaner = metricsCleaner;
	}

	// client schedule tetiklenmesini beklemek yerine kendi manuel olarak tetikleyebilir.
	@Override
	@PostMapping("/save")
	public ResponseEntity<ApiResponse<SystemMetricsLogDto>> saveAndGetMetrics() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.saveMetrics());
	}

	// tüm metrikleri toplar ancak veritabanına kaydetmez. sadece anlık alınır.
	@Override
	@GetMapping("/")
	public ResponseEntity<ApiResponse<SystemMetricsLogDto>> getMetrics() throws Exception {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getMetrics());
	}
 
	// sadece belirli metrikler ...
	@Override
	@GetMapping("/cpu")
	public ResponseEntity<ApiResponse<CpuMetricDto>> getCpuMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getCpuMetric());
	}

	@Override
	@GetMapping("/memory")
	public ResponseEntity<ApiResponse<MemoryMetricDto>> getMemoryMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getMemoryMetric());
	}

	@Override
	@GetMapping("/disk")
	public ResponseEntity<ApiResponse<DiskMetricDto>> getDiskMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getDiskMetric());
	}

	// sadece sistemin bilgilerini toplar.
	@Override
	@GetMapping("/system")
	public ResponseEntity<ApiResponse<SystemInfoDto>> getSystemInfo() throws Exception{
		return ApiResponse.ok(ResponseType.SYSTEM_INFO_COLLECTED, metricsService.getSystemInfo());
	}

	@Override
	@DeleteMapping("/log/{id}")
	public ResponseEntity<ApiResponse<Boolean>> deleteLogById(@PathVariable(name = "id") long id) {
		return ApiResponse.ok(ResponseType.METRICS_DELETED, metricsService.deleteLogById(id));
	}

	@Override
	@DeleteMapping("/clean")
	public ResponseEntity<ApiResponse<Integer>> cleanOldMetrics() {
		return ApiResponse.ok(ResponseType.LOGS_CLEANED, metricsCleaner.cleanOldMetrics());
	}

	@Override
	@GetMapping("/log/{id}")
	public ResponseEntity<ApiResponse<SystemMetricsLogDto>> getLogById(@PathVariable(name = "id") long id) {
		return ApiResponse.ok(ResponseType.METRICS_FOUND, metricsService.getLogById(id));
	}

	@Override
	@GetMapping("/snapshot")
	public ResponseEntity<ApiResponse<MetricsSnapshot>> buildSnapshot() {
		return ApiResponse.ok(ResponseType.BUILD_SNAPSHOT_SUCCESSFULLY, metricsService.buildSnapshot());
	}

	// filtreleme ile arama
	@Override
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<Page<SystemMetricsLogDto>>> findByCreatedAtBetween(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "12") Integer pageSize,
			@RequestParam(required = false, defaultValue = "desc") String sortedBy) {
		MetricsValidator.validateDateRange(startDate, endDate);
		LocalDateTime start = startDate.atStartOfDay();
		LocalDateTime end = endDate.plusDays(1).atStartOfDay();
		return ApiResponse.ok(ResponseType.METRICS_FOUND, metricsService.findByCreatedAtBetween(start, end, pageNumber, pageSize, sortedBy));
	}

	@Override
	@GetMapping("/gpu")
	public ResponseEntity<ApiResponse<List<GpuInfoDto>>> getGpuInfo() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getGpuInfo());
	}

	//sadece network bilgileri
	@Override
	@GetMapping("/network")
	public ResponseEntity<ApiResponse<NetworkMetricDto>> getNetworkMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getNetworkMetric());
	}
}