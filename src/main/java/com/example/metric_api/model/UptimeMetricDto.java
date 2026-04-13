package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UptimeMetricDto {

	private Long serviceUpTime;
	private Long OsUpTime;
	
}
