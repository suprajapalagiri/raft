package com.ebsco.dto;

import java.util.ArrayList;
import java.util.List;

import com.ebsco.entity.Module;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDTO {

	private Integer roleid;
	private String rolename;

	private Boolean isadmin;

	private Boolean isteamadmin;

	private List<Module> modules = new ArrayList<>();
}
