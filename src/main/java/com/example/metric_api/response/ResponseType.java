package com.example.metric_api.response;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public enum ResponseType {

	// metrics
	METRICS_COLLECTED("METRICS_COLLECTED", "Metrics is successfully collected.", HttpStatus.OK),
	METRICS_FOUND("METRICS_FOUND","Metrics is successfully found.",HttpStatus.FOUND),
	METRICS_DELETED("METRICS_DELETED", "Metrics is successfully deleted.", HttpStatus.OK),
	METRICS_NOT_COLLECTED("MERTRICS_NOT_COLLECTED","Something went wrong, metrics not collected.", HttpStatus.NOT_FOUND),
	METRICS_NOT_FOUND("METRICS_NOT_FOUND", "Something went wrong, metrics is not found.", HttpStatus.NOT_FOUND),
	METRICS_IS_INVALID("METRICS_IS_INVALID", "Something went wrong, metrics is invalid (null, NaN or negative).", HttpStatus.INTERNAL_SERVER_ERROR),

	// logs
	LOG_FOUND("LOG_FOUND", "Log is successfully found in database.", HttpStatus.FOUND),
	LOG_NOT_FOUND("LOG_NOT_FOUND", "Log is not found in database.", HttpStatus.NOT_FOUND),
	LOG_DELETED("LOG_DELETED", "Log is successfully deleted in database", HttpStatus.OK),
	LOGS_CLEANED("LOGS_CLEANED", "Logs succesfully cleaned in database.", HttpStatus.OK),

	BUILD_SNAPSHOT_SUCCESSFULLY("BUILD_SNAPSHOT_SUCCESFULLY", "Snapshot successfully written to json file.", HttpStatus.OK),

	DATE_TYPE_ERROR("DATE_TYPE_ERROR", "Missing date, dates is missing or typed wrong.", HttpStatus.BAD_REQUEST),

	// system
	SYSTEM_INFO_COLLECTED("SYSTEM_INFO_COLLECTED","System info successfully collected.", HttpStatus.OK),
	SYSTEM_INFO_NOT_COLLECTED("SYSTEM_INFO_NOT_COLLECTED","Something went wrong, system info not collected.", HttpStatus.NOT_FOUND),

	// single metrics
	CPU_INFO_NOT_COLLECTED("CPU_INFO_NOT_COLLECTED", "Something went wrong, CPU info not collected.", HttpStatus.NOT_FOUND),
	DISK_INFO_NOT_COLLECTED("DISK_INFO_NOT_COLLECTED", "Something went wrong, disk info not collected.", HttpStatus.NOT_FOUND),
	GPU_INFO_NOT_COLLECTED("GPU_INFO_NOT_COLLECTED", "Something went wrong, GPU info not collected", HttpStatus.NOT_FOUND),
	
	OS_METRICS_NOT_COLLECTED("OS_METRICS_NOT_FOUND","OS metrics is not collected.", HttpStatus.NOT_FOUND),
	CPU_METRICS_NOT_COLLECTED("CPU_METRICS_NOT_FOUND","Cpu metrics is not collected.", HttpStatus.NOT_FOUND),
	MEMORY_METRICS_NOT_COLLECTED("MEMORY_METRICS_NOT_FOUND","Memory metrics is not collected.", HttpStatus.NOT_FOUND),
	DISK_METRICS_NOT_COLLECTED("DISK_METRICS_NOT_FOUND","Disk metrics is not collected.", HttpStatus.NOT_FOUND),
	HOSTNAME_NOT_COLLECTED("HOSTNAME_NOT_FOUND", "hostname metric is not collected.", HttpStatus.NOT_FOUND);
	
	private String code;
	private String message;
	private HttpStatus status;
	
	private ResponseType(String code, String message, HttpStatus status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}
}
