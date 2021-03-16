package com.ebsco.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.entity.FullTextRequest;
import com.ebsco.service.FullTextService;
import com.ebsco.util.FullTextUtility;
import com.ebsco.validation.DtoValidationUtils;
import com.ebsco.validation.DtoValidationUtils.Optionality;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(description = "This Controller is used for the fullText service Operations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("raft/fulltext")
public class FullTextController {

	@Autowired
	FullTextService service;

	@ApiOperation(consumes = MediaType.APPLICATION_JSON_VALUE, value = "Gets all the PMid fields based on the given pmids")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 200, message = "Data retrieved successfully") })
	@GetMapping("/search/{ids}/{userName}")
	public ResponseEntity<?> get(
			@ApiParam(value = "for this you need to send the multiple PMids seperated with comma") @PathVariable @Valid @NotNull String ids,
			@PathVariable @Valid @NotNull String userName) {
		DtoValidationUtils.validate(ids, Optionality.REQUIRED);
		log.debug("Given input for the PM ids {%s}", ids);
		List<FullTextRequest> listOfRequests = FullTextUtility.processIds(ids).stream()
				.map(n -> service.findByPmId(n, userName)).collect(Collectors.toList());
		Iterables.removeIf(listOfRequests, Predicates.isNull());
		if (listOfRequests.isEmpty())
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.ok(listOfRequests);

	}
}
