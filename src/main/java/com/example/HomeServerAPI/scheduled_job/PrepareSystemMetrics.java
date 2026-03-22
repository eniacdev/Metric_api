package com.example.HomeServerAPI.scheduled_job;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.HomeServerAPI.log.APILog;
import com.example.HomeServerAPI.model.CpuDto;
import com.example.HomeServerAPI.model.DiskDto;
import com.example.HomeServerAPI.model.MemoryDto;
import com.example.HomeServerAPI.model.OsDto;
import com.example.HomeServerAPI.model.SystemLogDto;
import com.sun.management.OperatingSystemMXBean;

@Service
public class PrepareSystemMetrics {

    
	
	//prepareOsMetrics
	//prepareCpuMetrics
	//prepareMempryMetrics
	//prepareDiskMetrics
	//getHostname

	// gerçekleştirilen işlemleri loglara yansıtmak için kullandım.
	// importlara dikkat.
	private static final Logger log = LoggerFactory.getLogger(PrepareSystemMetrics.class);
	OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	
	public SystemLogDto prepareSystemMetrics() throws Exception{
		SystemLogDto metric = new SystemLogDto();
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
		return osDto;
	}
	
	public CpuDto collectCpuMetrics() {
		CpuDto cpuDto = new CpuDto();
		cpuDto.setCpuCores(osBean.getAvailableProcessors());
		cpuDto.setProcessCpuLoad(osBean.getProcessCpuLoad() * 100);
		cpuDto.setSystemCpuLoad(osBean.getSystemCpuLoad() * 100);
		cpuDto.setSystemAverageLoad(osBean.getSystemLoadAverage());
		return cpuDto;
	}
	
	public MemoryDto collectMemoryMetrics() {
		MemoryDto memoryDto = new MemoryDto();
		memoryDto.setFreeMemory(osBean.getFreeMemorySize());
		memoryDto.setTotalMemory(osBean.getTotalMemorySize());
		memoryDto.setMemoryUsage(memoryDto.getTotalMemory() - memoryDto.getFreeMemory());
		return memoryDto;
	}
	
	public DiskDto collectDiskMetrics() {
		DiskDto diskDto = new DiskDto();
		File root = new File("/");
		diskDto.setFreeDisk(root.getFreeSpace());
		diskDto.setTotalDisk(root.getTotalSpace());
		diskDto.setDiskUsage(diskDto.getTotalDisk() - diskDto.getFreeDisk());
		return diskDto;
	}
	
	public String getHostname() throws Exception{
		if(InetAddress.getLocalHost().getHostName() != null) {
			String hostName = InetAddress.getLocalHost().getHostName();
		}
		return null;
	}

}
