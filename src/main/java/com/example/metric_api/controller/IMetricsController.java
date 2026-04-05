package com.example.metric_api.controller;

 
import org.springframework.http.ResponseEntity;

import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.model.OsDto;
import com.example.metric_api.model.SystemLogDto;
import com.example.metric_api.model.UptimeMetricDto;
import com.example.metric_api.response.ApiResponse;


public interface IMetricsController {

	public ResponseEntity<ApiResponse<SystemLogDto>> prepareAndCreateMetrics();
	public ResponseEntity<ApiResponse<UptimeMetricDto>> getUptimeMetric() throws Exception;
	public ResponseEntity<ApiResponse<OsDto>> getOsMetric();
	public ResponseEntity<ApiResponse<CpuDto>> getCpuMetric();
	public ResponseEntity<ApiResponse<MemoryDto>> getMemoryMetric();
	public ResponseEntity<ApiResponse<DiskDto>> getDiskMetric();
}
