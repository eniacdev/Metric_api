package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryDto {
	
	private Long memoryUsage;
	private Long freeMemory;
	private Long totalMemory;
}
