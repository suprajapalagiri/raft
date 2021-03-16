package com.ebsco.serviceimpl;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ebsco.dto.Response;
import com.ebsco.entity.Module;
import com.ebsco.exception.DataNotFoundException;
import com.ebsco.repository.ModuleRepository;
import com.ebsco.service.ModuleService;
import com.ebsco.util.PaginationUtils;
import com.ebsco.validation.DtoValidationUtils;
import com.ebsco.validation.DtoValidationUtils.Optionality;

@Service(value = "v1ModuleServiceImpl")
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;

	@Override
	public Response getAllModules(@NotNull Integer pageSize, @NotNull Integer pageNumber) {
		Integer resultPageSize = PaginationUtils.getPageSize(pageSize, pageNumber);
		Response response = Response.builder().build();

		try {
			List<Module> allModules = moduleRepository.getAllModules(resultPageSize, pageSize);
			int totalRecords = moduleRepository.getmoduleCount();

			Integer toalPages = PaginationUtils.getTotalPages(totalRecords, pageSize);

			response.setResult(allModules);
			response.setHttpStatus(HttpStatus.OK);
			response.setRes("list retrieved successfully");
			response.setTotalPages(toalPages);
			response.setTotalRecords(totalRecords);
		} catch (Exception e) {
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public Module saveModule(Module module) {
		if (Objects.isNull(module)) {
			throw new DataNotFoundException("The Data which is used to create module is require but found null");
		}

		return moduleRepository.save(module);
	}

	@Override
	public Module getModuleById(@NotNull Integer moduleId) {
		DtoValidationUtils.validate(moduleId, Optionality.REQUIRED);
		return moduleRepository.getOne(moduleId);
	}

	@Override
	public void deleteModuleById(@NotNull Integer moduleId) {

		DtoValidationUtils.validate(moduleId, Optionality.REQUIRED);
		if (getModuleById(moduleId) != null)
			moduleRepository.deleteById(moduleId);

	}

}
