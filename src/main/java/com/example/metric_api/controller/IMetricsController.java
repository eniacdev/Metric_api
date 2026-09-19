package com.example.metric_api.controller;

 
import com.example.metric_api.dto.*;
import com.example.metric_api.model.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.metric_api.response.ApiResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface IMetricsController {

	public ResponseEntity<ApiResponse<SystemMetricsLogDto>> saveAndGetMetrics();
	public ResponseEntity<ApiResponse<SystemInfoDto>> getSystemInfo() throws Exception;
	public ResponseEntity<ApiResponse<NetworkMetricDto>> getNetworkMetric();
	public ResponseEntity<ApiResponse<SystemMetricsLogDto>> getMetrics() throws Exception;
	public ResponseEntity<ApiResponse<CpuMetricDto>> getCpuMetric();
	public ResponseEntity<ApiResponse<MemoryMetricDto>> getMemoryMetric();
	public ResponseEntity<ApiResponse<DiskMetricDto>> getDiskMetric();
	public ResponseEntity<ApiResponse<Boolean>> deleteLogById(long id);
	public ResponseEntity<ApiResponse<SystemMetricsLogDto>> getLogById(long id);
	public ResponseEntity<ApiResponse<MetricsSnapshot>> buildSnapshot();
	public ResponseEntity<ApiResponse<Page<SystemMetricsLogDto>>> findByCreatedAtBetween(
			LocalDate startDate,LocalDate endDate,Integer pageNumber,Integer pageSize, String sortedBy);
	public ResponseEntity<ApiResponse<List<GpuInfoDto>>> getGpuInfo();
	public ResponseEntity<ApiResponse<Integer>> cleanOldMetrics();
}
