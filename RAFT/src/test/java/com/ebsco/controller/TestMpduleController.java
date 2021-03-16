package com.ebsco.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ebsco.exception.DataValidationException;
import com.ebsco.service.ModuleService;

class TestMpduleController {
	@InjectMocks
	private ModuleController controller = new ModuleController();

	@Mock
	private ModuleService moduleService;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void test_saveModule_nullBody() {

		assertThrows(DataValidationException.class, () -> controller.saveModule(null));

	}

}
