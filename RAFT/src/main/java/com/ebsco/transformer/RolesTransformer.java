package com.ebsco.transformer;

import com.ebsco.dto.RoleDTO;
import com.ebsco.entity.Role;

public class RolesTransformer {

	public static Role fromDtoToEntity(RoleDTO dto) {

		return Role.builder().isadmin(dto.getIsadmin()).isteamadmin(dto.getIsteamadmin()).modules(dto.getModules())
				.rolename(dto.getRolename()).roleid(dto.getRoleid()).build();

	}

	public static RoleDTO fromEntityToDTO(Role roles) {

		return RoleDTO.builder().isadmin(roles.getIsadmin()).isteamadmin(roles.getIsteamadmin())
				.modules(roles.getModules()).rolename(roles.getRolename()).roleid(roles.getRoleid()).build();

	}

}
