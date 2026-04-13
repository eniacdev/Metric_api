package com.example.metric_api.controller;

 
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.model.OsDto;
import com.example.metric_api.model.SystemInfo;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.model.UptimeMetricDto;
import com.example.metric_api.response.ApiResponse;


public interface IMetricsController {

	public ResponseEntity<ApiResponse<SystemMetricsDto>> prepareAndSaveMetrics();
	public ResponseEntity<ApiResponse<SystemMetricsDto>> getAllMetrics() throws Exception;
	public ResponseEntity<ApiResponse<SystemInfo>> prepareAndGetSystemInfo() throws Exception;
	public ResponseEntity<ApiResponse<CpuDto>> getCpuMetric();
	public ResponseEntity<ApiResponse<MemoryDto>> getMemoryMetric();
	public ResponseEntity<ApiResponse<DiskDto>> getDiskMetric();
}
