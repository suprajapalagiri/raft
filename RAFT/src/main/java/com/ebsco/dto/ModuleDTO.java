package com.ebsco.dto;

import com.ebsco.entity.Module;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ModuleDTO {
	private Integer moduleId;
	private String moduleName;
	private Module parentId;
	private String modulePath;
	private String iconName;
	private String imagePath;

	private Integer orderNumber;
	private Boolean openInChildWindow;
}
