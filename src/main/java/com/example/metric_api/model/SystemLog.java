package com.example.metric_api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metrics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long logId;
	
	@Column(name = "timeStamp")
	private LocalDateTime timeStamp;
	
	// -- OS --
	@Column(name = "osName")
	private String osName;
		
	@Column(name = "osVersion")
	private String osVersion;
	
	// -- CPU --
	@Column(name = "cpuCores")
	private int cpuCores;
	
	@Column(name = "processCpuLoad")
	private double processCpuLoad;
	
	@Column(name = "systemcpuLoad")
	private double systemCpuLoad;
	
	@Column(name = "systemAverageLoad")
	private double systemAverageLoad;
	
	// -- MEMORY --
	@Column(name = "memoryUsage")
	private long memoryUsage;
	
	@Column(name = "freeMemory")
	private long freeMemory;
	
	@Column(name = "totalMemory")
	private long totalMemory;
	
	// -- DISK --
	@Column(name = "diskUsage")
	private long diskUsage;
	
	@Column(name = "freeDisk")
	private long freeDisk;
	
	@Column(name = "totalDisk")
	private long totalDisk;
}
