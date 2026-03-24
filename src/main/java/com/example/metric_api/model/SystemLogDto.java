package com.example.metric_api.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemLogDto {

	private LocalDateTime timeStamp;
	private String hostName;
	private OsDto os;
	private CpuDto cpu;
	private MemoryDto memory;
	private DiskDto disk;
}
