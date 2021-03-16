package com.ebsco.service;

import javax.validation.constraints.NotNull;

import com.ebsco.dto.Response;
import com.ebsco.entity.Module;

public interface ModuleService {

	Response getAllModules(@NotNull Integer pageSize, @NotNull Integer pageNumber);

	Module saveModule(@NotNull Module module);

	Module getModuleById(@NotNull Integer moduleId);

	void deleteModuleById(@NotNull Integer moduleId);

}
