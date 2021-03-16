package com.ebsco.validation;

import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;

import com.ebsco.dto.ModuleDTO;
import com.ebsco.dto.RoleDTO;
import com.ebsco.dto.SearchTemplate;
import com.ebsco.dto.UsersDTO;
import com.ebsco.exception.DataNotFoundException;
import com.ebsco.exception.DataValidationException;
import com.ebsco.model.Login;

public class DtoValidationUtils {

	public enum Optionality {
		REQUIRED, OPTIONAL;
	}

	public static void validate(Object string, Optionality optionality) {

		if (optionality == Optionality.REQUIRED) {

			if (string == null)
				throw new DataValidationException("The Required value is not found");
			if (string instanceof String) {
				if (((String) string).isEmpty())
					throw new DataValidationException("The Value is required but found Empty");
			}
		}
	}

	public static void validateLogin(Login login) {

		if (Objects.isNull(login))
			throw new DataNotFoundException("login details required but found null");

		validate(login.getUserName(), Optionality.REQUIRED);
		validate(login.getPassword(), Optionality.REQUIRED);

	}

	public static void validateSearch(SearchTemplate searchTemplate) {
		if (Objects.isNull(searchTemplate)) {
			throw new DataNotFoundException("Search criteria data is not found ");
		}

		validate(searchTemplate.getPageNumber(), Optionality.REQUIRED);
		validate(searchTemplate.getPageNumber(), Optionality.REQUIRED);

	}

	public static void validateRole(RoleDTO dto) {
		if (Objects.isNull(dto)) {
			throw new DataNotFoundException("The Required data for creating role is not found");
		}
		validate(dto.getRolename(), Optionality.REQUIRED);
		if (dto.getRoleid() != null && !NumberUtils.isCreatable(String.valueOf(dto.getRoleid()))) {
			throw new DataFormattingException("This property must contain only Number but found String");
		}

	}

	public static void validateUser(UsersDTO userDTO) {

		if (Objects.isNull(userDTO)) {
			throw new DataValidationException("The data used to create a user is required but found null");

		}

	}

	private static void validateEntity(Object obj) {
		if (Objects.isNull(obj)) {

		}
	}

	public static void validateModule(ModuleDTO dto) {
		if (Objects.isNull(dto)) {
			throw new DataValidationException("The data used to create a module is required but found null");

		}
		validate(dto.getModuleName(), Optionality.REQUIRED);
	}

}
