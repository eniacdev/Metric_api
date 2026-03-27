package com.example.metric_api.scheduled_job;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.model.OsDto;
import com.example.metric_api.model.SystemLogDto;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;

@Service
public class PrepareSystemMetrics {

	// gerçekleştirilen işlemleri loglara yansıtmak için kullandım.
	// importlara dikkat.
	private static final Logger log = LoggerFactory.getLogger(PrepareSystemMetrics.class);
	OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	
	public SystemLogDto prepareSystemMetrics() throws Exception{
		
		SystemLogDto metric = new SystemLogDto();
		
		log.warn("the metrics is being preparing");
		
		metric.setOs(collectOsMetrics());
		metric.setCpu(collectCpuMetrics());
		metric.setMemory(collectMemoryMetrics());
		metric.setDisk(collectDiskMetrics());
		metric.setTimeStamp(LocalDateTime.now());
		metric.setHostName(getHostname());
		
		return metric;
	}
	
	public OsDto collectOsMetrics() {
		
		OsDto osDto = new OsDto();
		
		osDto.setOsName(osBean.getName());
		osDto.setOsVersion(osBean.getVersion());
		
		if(osDto.getOsName() == null && osDto.getOsVersion() == null) {
			throw new BaseException(ResponseType.OS_METRICS_NOT_FOUND);
		}
		return osDto;
	}
	
	public CpuDto collectCpuMetrics() {
		
		CpuDto cpuDto = new CpuDto();
		
		cpuDto.setCpuCores(osBean.getAvailableProcessors());
		cpuDto.setProcessCpuLoad(osBean.getProcessCpuLoad() * 100);
		cpuDto.setSystemCpuLoad(osBean.getSystemCpuLoad() * 100);
		cpuDto.setSystemAverageLoad(osBean.getSystemLoadAverage());
		
		if(cpuDto.getCpuCores() == null && cpuDto.getProcessCpuLoad() == null &&
		   cpuDto.getSystemAverageLoad() == null && cpuDto.getSystemCpuLoad() == null) {
			throw new BaseException(ResponseType.CPU_METRICS_NOT_FOUND);
		}
		return cpuDto;
	}
		
	
	public MemoryDto collectMemoryMetrics() {
		
		MemoryDto memoryDto = new MemoryDto();
		
		memoryDto.setFreeMemory(osBean.getFreeMemorySize());
		memoryDto.setTotalMemory(osBean.getTotalMemorySize());
		memoryDto.setMemoryUsage(memoryDto.getTotalMemory() - memoryDto.getFreeMemory());
		
		if(memoryDto.getFreeMemory() == null && memoryDto.getMemoryUsage() == null &&
		   memoryDto.getTotalMemory() == null) {
			throw new BaseException(ResponseType.MEMORY_METRICS_NOT_FOUND);
		}
		
		return memoryDto;
	}
	
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
	
	public String getHostname() throws Exception{
		String hostName = InetAddress.getLocalHost().getHostName();
		if(hostName == null) {
			throw new BaseException(ResponseType.HOSTNAME_NOT_FOUND);
		}
		return hostName;
	}
}
