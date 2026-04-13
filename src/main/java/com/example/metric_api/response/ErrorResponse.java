package com.example.metric_api.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class ErrorResponse {

	//error response model
	private LocalDateTime timestamp;
	private String code;
	private String message;
}
