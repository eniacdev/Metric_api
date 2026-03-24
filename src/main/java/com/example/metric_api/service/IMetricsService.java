package com.example.metric_api.service;
import com.example.metric_api.model.SystemLogDto;

public interface IMetricsService {

	public SystemLogDto prepareAndCreateMetrics();
}
