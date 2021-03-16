package com.ebsco.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private ExceptionMessage status;
	private String errorCode;
	private String errorMessage;
	private String exceptionType;
	private Object result;
	private Object result1;
	private HttpStatus httpStatus;
	private String status2;
	private String res;
	private Integer totalPages;
	private Integer totalRecords;
	private String encodedString;

	public Response(ExceptionMessage status, Object result, Object result1) {
		super();
		this.status = status;
		this.result = result;
		this.result1 = result1;
	}

	public Response(ExceptionMessage status, String res) {
		super();
		this.status = status;
		this.res = res;
	}

	public Response(ExceptionMessage status, Object result) {
		super();
		this.status = status;
		this.result = result;

	}

	public Response(ExceptionMessage status) {
		super();
		this.status = status;

	}

	public Response(Object result, ExceptionMessage status) {
		this.status = status;
		this.result = result;
	}

	public Response(ExceptionMessage status, String errorCode, String errorMessage, String exceptionType) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.exceptionType = exceptionType;
	}

	public Response(ExceptionMessage status, String errorCode, String errorMessage) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;

	}

	public Response(ExceptionMessage status, Object result, String encodedString) {
		super();
		this.status = status;
		this.result = result;
		this.encodedString = encodedString;
	}

	public Response(String status2, HttpStatus httpStatus, String res) {
		this.status2 = status2;
		this.res = res;
		this.httpStatus = httpStatus;

	}

	public Response(HttpStatus httpStatus, String res) {
		this.res = res;
		this.httpStatus = httpStatus;

	}

}