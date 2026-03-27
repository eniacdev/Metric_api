package com.example.metric_api.exception_handler;

import com.example.metric_api.response.ResponseType;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

	private final ResponseType responseType;
	
	public BaseException(ResponseType responseType) {
		super(responseType.getMessage());
		this.responseType = responseType;
	}
}
