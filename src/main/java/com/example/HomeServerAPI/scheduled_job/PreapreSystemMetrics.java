package com.example.HomeServerAPI.scheduled_job;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.example.HomeServerAPI.model.CpuDto;
import com.example.HomeServerAPI.model.DiskDto;
import com.example.HomeServerAPI.model.MemoryDto;
import com.example.HomeServerAPI.model.OsDto;
import com.example.HomeServerAPI.model.SystemLogDto;
import com.sun.management.OperatingSystemMXBean;

@Service
public class PreapreSystemMetrics {
	
	public SystemLogDto preapreSystemMetrics() throws Exception{
		
	System.out.println("metrikler toplanmaya başlandı");
	
	//OperatingSystemMXBean sayesinde metrikleri alabilir ve işlenebilir.
	OperatingSystemMXBean osBean =(OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	//DISK metrikleri toplamak için File'dan yardım aldım.
	File root = new File("/");
	
	//DTO'lar.
	SystemLogDto metric = new SystemLogDto();
	OsDto osDto = new OsDto();
	CpuDto cpuDto = new CpuDto();
	MemoryDto memoryDto = new MemoryDto();
	DiskDto diskDto = new DiskDto();
	
	String hostname = InetAddress.getLocalHost().getHostName();
	
	// OS metrikleri.
	osDto.setOsName(osBean.getName());
	osDto.setOsVersion(osBean.getVersion());
	
	// CPU metrikleri.
	cpuDto.setCpuCores(osBean.getAvailableProcessors());
	cpuDto.setProcessCpuLoad(osBean.getProcessCpuLoad() * 100);
	cpuDto.setSystemCpuLoad(osBean.getSystemCpuLoad() * 100);
	cpuDto.setSystemAverageLoad(osBean.getSystemLoadAverage());
	
	//RAM metrikleri.
	memoryDto.setFreeMemory(osBean.getFreeMemorySize());
	memoryDto.setTotalMemory(osBean.getTotalMemorySize());
	memoryDto.setMemoryUsage(memoryDto.getTotalMemory() - memoryDto.getFreeMemory());
	
	//DISK metrikleri.
	diskDto.setFreeDisk(root.getFreeSpace());
	diskDto.setTotalDisk(root.getTotalSpace());
	diskDto.setDiskUsage(diskDto.getTotalDisk() - diskDto.getFreeDisk());
	
	//metriklerin DTO olarak kaydedilmesi.
	metric.setOs(osDto);
	metric.setCpu(cpuDto);
	metric.setMemory(memoryDto);
	metric.setDisk(diskDto);
	metric.setTimeStamp(LocalDateTime.now());
	metric.setHostName(hostname);
	 
	System.out.println("metrikler toplandı");
	 
	return metric;
	 
	}

}
