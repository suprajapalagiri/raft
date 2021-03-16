package com.ebsco.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "userdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;

	@Column(name = "empid")
	private String empId;

	private String password;

	@Column(name = "isactive")
	private Boolean isActive;

	@Column(name = "empname")
	private String empName;

	@Column(name = "isteamhead")
	private Boolean isTeamHead;

	private String email;

	@Column(name = "costcenter")
	private String costCenter;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userroles", joinColumns = @JoinColumn(name = "UserID"), inverseJoinColumns = @JoinColumn(name = "RoleID"))
	private List<Role> roles;

}
