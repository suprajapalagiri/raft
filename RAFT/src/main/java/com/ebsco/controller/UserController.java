package com.ebsco.controller;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.ebsco.dto.ErrorDto;
import com.ebsco.dto.ExceptionMessage;
import com.ebsco.dto.Response;
import com.ebsco.dto.SearchTemplate;
import com.ebsco.dto.UsersDTO;
import com.ebsco.entity.UserDetails;
import com.ebsco.exception.DataNotFoundException;
import com.ebsco.exception.DataValidationException;
import com.ebsco.model.Login;
import com.ebsco.service.UserService;
import com.ebsco.transformer.UserDetailsTransformer;
import com.ebsco.validation.DataFormattingException;
import com.ebsco.validation.DtoValidationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(description = "This Controller is used to perform  User Related Operations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("raft/userServices")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping(value = "/login")
	@ApiOperation(consumes = "application/json", value = "Login service")
	public ResponseEntity<?> login(@Valid @NotNull @RequestBody Login login) {
		DtoValidationUtils.validateLogin(login);
		UserDetails value = null;
		value = service.findByUserNameAndPassword(login.getUserName(), login.getPassword());

		if (Objects.isNull(value)) {
			throw new DataNotFoundException("No user found");
		}
//		Response response = new Response(ExceptionMessage.OK, UserDetailsTransformer.fromEntityToDto(value));
		return ResponseEntity.ok(UserDetailsTransformer.fromEntityToDto(value));
	}

	@ApiResponses(value = { @ApiResponse(code = 400, message = "User Not Saved beacuase of Bad Request"),
			@ApiResponse(code = 201, message = "User created Successfully!!") })

	@PostMapping("/save")
	@ApiOperation(consumes = "application/json", produces = "application/json", value = "User Save Service")
	public Response saveUser(
			@ApiParam(name = "users", value = "This is the DTO which is related to User Saving") @Valid @NotNull @RequestBody UsersDTO userDTO) {
		DtoValidationUtils.validateUser(userDTO);

		UserDetails savedUserDetails = service.save(UserDetailsTransformer.fromDtoToUser(userDTO));

		return new Response(ExceptionMessage.Created, savedUserDetails);
	}

	@PostMapping("/search")
	@ApiOperation(consumes = "application/json", value = "This is to get the list of users based on the pageNumber and pageSize")
	public Response userSearch(@Valid @NotNull @RequestBody SearchTemplate search) {

		return service.findAll(search);

	}

	// Exception Handlers

	@ExceptionHandler(DataValidationException.class)
	public ResponseEntity<ErrorDto> dataValidationExceptionHandler(WebRequest httpRequest,
			DataValidationException dataValidationException) {

		return handle(dataValidationException, HttpStatus.BAD_REQUEST, getRequestUri(httpRequest));
	}

	@ExceptionHandler(DataValidationException.class)
	public ResponseEntity<ErrorDto> dataFormattingExceptionHandler(WebRequest httpRequest,
			DataFormattingException dataFormattingException) {

		return handle(dataFormattingException, HttpStatus.BAD_REQUEST, getRequestUri(httpRequest));
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
