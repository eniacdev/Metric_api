package com.example.metric_api.service;
import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.model.OsDto;
import com.example.metric_api.model.SystemInfo;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.model.UptimeMetricDto;
import com.example.metric_api.scheduled_job.prepare.PrepareCpuMetric;

public interface IMetricsService {

	public SystemMetricsDto prepareAndGetMetrics();
	public SystemInfo prepareAndGetSystemInfo() throws Exception;
	public CpuDto getCpuMetric(); 
	public MemoryDto getMemoryMetric();
	public DiskDto getDiskMetric();
	
}
