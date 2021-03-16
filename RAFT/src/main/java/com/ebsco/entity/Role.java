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
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor 
@Entity(name = "roles")
public class Role {

	@Id 
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleIdseq")
	@SequenceGenerator(initialValue = 5, allocationSize = 1, name = "roleIdseq", sequenceName = "roleidsequence")
	private Integer roleid;
	@Column(length = 200)
	@NotNull
	private String rolename;

	private Boolean isadmin;

	private Boolean isteamadmin;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rolemodules", joinColumns = @JoinColumn(name = "RoleID"), inverseJoinColumns = @JoinColumn(name = "ModuleID"))
	private List<Module> modules;
	
	

}
