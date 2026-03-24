package com.example.metric_api.controller;

 
import com.example.metric_api.model.SystemLogDto;

public interface IMetricsController {

	public SystemLogDto prepareAndCreateMetrics();
}
