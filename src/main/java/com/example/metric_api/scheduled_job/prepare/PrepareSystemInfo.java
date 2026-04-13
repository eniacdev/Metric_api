package com.example.metric_api.scheduled_job.prepare;

import com.example.metric_api.model.OsDto;
import com.example.metric_api.model.SystemInfo;
import com.example.metric_api.model.UptimeMetricDto;

public class PrepareSystemInfo {

	
	public SystemInfo collectSystemInfo() throws Exception{
		
		SystemInfo systemInfo = new SystemInfo();
		
		PrepareOsMetric osMetric = new PrepareOsMetric();
		PrepareUptimeMetric uptimeMetric = new PrepareUptimeMetric();
		PrepareHostnameMetric hostnameMetric = new PrepareHostnameMetric();
		
		systemInfo.setUptime(uptimeMetric.collectUptimeMetric());
		systemInfo.setHostname(hostnameMetric.getHostname());
		systemInfo.setOs(osMetric.collectOsMetrics());
		
		return systemInfo;
	}
}
