package com.example.demo.app;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.ManageDatesService;

public class ManageDatesRestControllerTest {

	@Mock
	private ManageDatesService sut;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	
}
