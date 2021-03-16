package com.ebsco.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.ebsco.dto.ErrorDto;
import com.ebsco.exception.DataNotFoundException;
import com.ebsco.exception.DataValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomnExceptionHandlers {

	@ExceptionHandler(DataValidationException.class)
	public ResponseEntity<ErrorDto> dataValidationExceptionHandler(WebRequest httpRequest,
			DataValidationException dataValidationException) {

		return handle(dataValidationException, HttpStatus.BAD_REQUEST, getRequestUri(httpRequest));
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorDto> dataNotFoundExceptionHandler(WebRequest httpRequest,
			DataNotFoundException dataValidationException) {

		return handle(dataValidationException, HttpStatus.BAD_REQUEST, getRequestUri(httpRequest));

	}

	private String getRequestUri(WebRequest request) {
		String uri = null;
		if (request instanceof ServletWebRequest) {
			uri = String.valueOf(((ServletWebRequest) request).getHttpMethod());
			if (((ServletWebRequest) request).getRequest() != null) {
				uri += " " + ((ServletWebRequest) request).getRequest().getRequestURI();
			}
		}
		return uri;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected ResponseEntity<ErrorDto> handle(Throwable t, HttpStatus httpStatus, String requestUri) {
		String type = t.getClass().getSimpleName();
		String description = t.getMessage() != null ? t.getMessage() : "Unknown error";
		if (httpStatus.is5xxServerError())
			log.error(String.format("Encountered unexpected error (code: %s, type: %s, message: %s, uri: %s)",
					httpStatus, type, t.getMessage(), requestUri), t);
		else if (httpStatus != HttpStatus.NOT_FOUND)
			log.warn(String.format("Encountered unexpected error (code: %s, type: %s, message: %s, uri: %s)",
					httpStatus, type, t.getMessage(), requestUri));
		ErrorDto error = new ErrorDto();
		error.setStatusCode(httpStatus.value());
		error.setType(type);
		error.setDescription(description);
		return new ResponseEntity(error, httpStatus);
	}

}
