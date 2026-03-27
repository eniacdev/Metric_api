package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuDto {
	
	private Integer cpuCores;
	private Double processCpuLoad;
	private Double systemCpuLoad;
	private Double systemAverageLoad;
}
