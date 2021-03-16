package com.ebsco.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.dto.ModuleDTO;
import com.ebsco.dto.Response;
import com.ebsco.dto.SearchTemplate;
import com.ebsco.entity.Module;
import com.ebsco.service.ModuleService;
import com.ebsco.transformer.ModuleTransformer;
import com.ebsco.validation.DtoValidationUtils;
import com.ebsco.validation.DtoValidationUtils.Optionality;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "this Controller is used to perform all the operations which can be performed by Module", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/raft/module")
public class ModuleController {

	@Autowired
	@Qualifier(value = "v1ModuleServiceImpl")
	private ModuleService moduleService;

	@PostMapping("/search")
	@ApiOperation(value = "this endppint is used to search the modules based on the given searchTemplate")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "list of modules data retrieved successfully!!"),
			@ApiResponse(code = 400, message = "unExcpected error occured because of bad Request given") })
	public ResponseEntity<Object> findAll(@NotNull @RequestBody SearchTemplate searchTemplate) {

		DtoValidationUtils.validateSearch(searchTemplate);
		Response response = Response.builder().build();

		try {
			response = moduleService.getAllModules(searchTemplate.getPageSize(), searchTemplate.getPageNumber());
		} catch (Exception e) {
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			response.setErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(response);

	}

	@PostMapping("/v1/save")
	@ApiOperation(value = "this endppint is used to save or update the given module")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "module Data is created or updated successfully!!"),
			@ApiResponse(code = 400, message = "unExcpected error occured because of bad Request given") })
	public ResponseEntity<Object> saveModule(@Valid @RequestBody ModuleDTO dto) {
		DtoValidationUtils.validateModule(dto);
		Response response = Response.builder().build();
		try {
			Module savedModule = moduleService.saveModule(ModuleTransformer.fromDTOToEntity(dto));
			response.setResult(savedModule);
			response.setHttpStatus(HttpStatus.CREATED);
			response.setRes("The Module is successfully created or Updated");
			return ResponseEntity.created(new URI("/raft/module/save"))
					.body(ModuleTransformer.fromEntityToDTO(savedModule));
		} catch (Exception e) {
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);

		}

	}

	@ApiOperation(value = "this is to find the module by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "module is retrieved successfully"),
			@ApiResponse(code = 400, message = "An Unexpected error occured") })
	@GetMapping("/{id}")
	public ResponseEntity<Object> getModuleById(@PathVariable Integer id) {
		DtoValidationUtils.validate(id, Optionality.REQUIRED);
		try {
			Module module = moduleService.getModuleById(id);
			return ResponseEntity.ok(Response.builder().result(module).httpStatus(HttpStatus.OK).build());
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(Response.builder().httpStatus(HttpStatus.BAD_REQUEST).errorMessage(e.getMessage()).build());
		}

	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "This is to delete the Module given by id")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "the module is dleted successfully"),
			@ApiResponse(code = 400, message = "unexcepted error occured while deleting module") })
	public ResponseEntity<Object> deleteModule(@PathVariable Integer id) {
		DtoValidationUtils.validate(id, Optionality.REQUIRED);
		try {

			moduleService.deleteModuleById(id);

			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
