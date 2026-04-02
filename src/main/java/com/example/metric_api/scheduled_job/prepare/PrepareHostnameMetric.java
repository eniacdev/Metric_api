package com.example.metric_api.scheduled_job.prepare;

import java.net.InetAddress;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.response.ResponseType;

public class PrepareHostnameMetric {
	
	public String getHostname() throws Exception{
		String hostName = InetAddress.getLocalHost().getHostName();
		if(hostName == null) {
			throw new BaseException(ResponseType.HOSTNAME_NOT_FOUND);
		}
		return hostName;
	}

}
