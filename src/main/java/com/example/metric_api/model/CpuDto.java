package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuDto {
	
	private int cpuCores;
	private double processCpuLoad;
	private double systemCpuLoad;
	private double systemAverageLoad;
}
