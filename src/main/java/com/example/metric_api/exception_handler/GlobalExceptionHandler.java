package com.example.metric_api.exception_handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.metric_api.response.ErrorResponse;
import com.example.metric_api.response.ResponseType;




@RestControllerAdvice
public class GlobalExceptionHandler {

	/* tüm hatalar burada yönetilmektedir.
	 * bu servisin validasyon hata yönetimine ihtiyacı yok, ancak ileride gerekebilir.
	 * 
	 */
	
	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(BaseException ex){
		
		ResponseType responseType = ex.getResponseType();
		
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(),
				responseType.getCode(),
				responseType.getMessage());
		
		return ResponseEntity.status(responseType.getStatus()).body(errorResponse);
	}
	
}
