package com.example.metric_api.response;

import org.springframework.http.HttpStatus;
import com.sun.net.httpserver.Authenticator.Success;
import com.sun.net.httpserver.HttpsConfigurator;
import lombok.Getter;

@Getter
public enum ResponseType {
	
	METRICS_COLLECTED("METRICS_COLLECTED", "Metrics is succesfuly collected.", HttpStatus.OK),
	METRICS_NOT_COLLECTED("MERTRICS_NOT_COLLECTED","Something went wrong, metrics not collected.", HttpStatus.NOT_FOUND),
	METRICS_NOT_FOUND("METRICS_NOT_FOUND", "Metrics is not found", HttpStatus.NOT_FOUND),
	
	OS_METRICS_NOT_COLLECTED("OS_METRICS_NOT_FOUND","Os metrics is not collected.", HttpStatus.NOT_FOUND),
	CPU_METRICS_NOT_COLLECTED("CPU_METRICS_NOT_FOUND","Cpu metrics is not collected.", HttpStatus.NOT_FOUND),
	MEMORY_METRICS_NOT_COLLECTED("MEMORY_METRICS_NOT_FOUND","Memory metrics is not collected.", HttpStatus.NOT_FOUND),
	DISK_METRICS_NOT_COLLECTED("DISK_METRICS_NOT_FOUND","Disk metrics is not collected.", HttpStatus.NOT_FOUND),
	HOSTNAME_NOT_COLLECTED("HOSTNAME_NOT_FOUND", "hostname metric is not collected.", HttpStatus.NOT_FOUND);
	
	
	private String code;
	private final String message;
	private HttpStatus status;
	
	private ResponseType(String code, String message, HttpStatus status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}
}
