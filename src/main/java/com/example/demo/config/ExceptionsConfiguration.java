package com.example.demo.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.ProductServiceException;

@RestControllerAdvice
public class ExceptionsConfiguration extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductServiceException.class)
	@ResponseBody
	public ResponseEntity<Object> handleControllerException(HttpServletRequest request, ProductServiceException ex) {

		return new ResponseEntity<Object>(ex.getMessage(), ex.getStatus());
	}

}
