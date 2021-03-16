package com.ebsco.service;

import javax.validation.constraints.NotNull;

import com.ebsco.dto.Response;
import com.ebsco.entity.Role;

public interface RolesService {

	Response findAll(Integer pageSize,Integer pageNumber);

	Response save(@NotNull Role role);

	Role findById(Integer roleId);

}
