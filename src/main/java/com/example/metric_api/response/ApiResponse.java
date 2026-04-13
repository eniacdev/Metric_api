package com.example.metric_api.response;

import java.sql.DatabaseMetaData;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse <T>{

	
	//sadece başarılı işlemler için response
	private LocalDateTime timestamp;
	private HttpStatus status;
	private T data;
	
	public static <T> ResponseEntity<ApiResponse<T>> ok(ResponseType responseType, T data){
		ApiResponse<T> response = new ApiResponse<T>(LocalDateTime.now(),responseType.getStatus(), data);
		
		return ResponseEntity.status(responseType.getStatus()).body(response);
	}
	
}
