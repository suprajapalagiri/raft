package com.ebsco.dto;

import java.util.List;

import com.ebsco.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

	private Integer userid;

	private String empId;

	private String password;

	private Boolean isActive;

	private String empName;

	private Boolean isTeamHead;

	private String email;

	private String costCenter;

	private List<Role> roles;

}
