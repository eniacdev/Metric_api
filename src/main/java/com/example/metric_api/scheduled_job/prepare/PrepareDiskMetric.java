package com.example.metric_api.scheduled_job.prepare;

import java.io.File;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.response.ResponseType;

public class PrepareDiskMetric {
	
	public DiskDto collectDiskMetrics() {
		
		DiskDto diskDto = new DiskDto();
		File root = new File("/");
		
		diskDto.setFreeDisk(root.getFreeSpace());
		diskDto.setTotalDisk(root.getTotalSpace());
		diskDto.setDiskUsage(diskDto.getTotalDisk() - diskDto.getFreeDisk());
		
		if(diskDto.getFreeDisk() == null && diskDto.getTotalDisk() == null &&
		   diskDto.getDiskUsage() == null) {
			throw new BaseException(ResponseType.DISK_METRICS_NOT_FOUND);
		}
		
		return diskDto;
	}

}
