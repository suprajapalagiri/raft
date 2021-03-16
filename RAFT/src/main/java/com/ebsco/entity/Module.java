package com.ebsco.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Entity(name = "modules")
public class Module {

	@Id
	@GeneratedValue(generator = "moduleIdseq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "moduleIdseq", sequenceName = "moduleIdsequence")
	@NotNull
	@Column(name = "moduleid")
	private Integer moduleId;
	@NotNull
	@Column(name = "modulename", length = 100)
	private String moduleName;
	@OneToOne
	@JoinColumn(name = "parentid")
	private Module parentId;
	@Column(name = "modulepath", length = 200)
	private String modulePath;
	@Column(name = "iconname", length = 200)
	private String iconName;
	@Column(name = "imagepath", length = 200)
	private String imagePath;

	@Column(name = "ordernumber")
	private Integer orderNumber;
	@Column(name = "openinchildwindow")
	private Boolean openInChildWindow;

}
