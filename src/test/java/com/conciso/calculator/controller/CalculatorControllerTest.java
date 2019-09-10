package com.conciso.calculator.controller;

import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.conciso.calculator.model.CalculatorSvcRequest;
import com.conciso.calculator.model.CalculatorSvcResponse;
import com.conciso.calculator.service.CalculatorOperationsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private CalculatorController calculatorController;

	@Mock
	private CalculatorOperationsService calculatorService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(calculatorController).build();
	}

	@WithMockUser("USER")
	@Test
	public void find_login_ok() throws Exception {
		// Request creation
		CalculatorSvcRequest calculatorSvcRequest = new CalculatorSvcRequest();
		ArrayList<Integer> operands = new ArrayList<Integer>();
		operands.add(1);
		operands.add(2);
		operands.add(3);
		calculatorSvcRequest.setOperands(operands);

		// Response creation
		CalculatorSvcResponse calculatorSvcResponse = new CalculatorSvcResponse();
		calculatorSvcResponse.setActualResult(6.0);

		when(calculatorService.add(calculatorSvcRequest, calculatorSvcResponse)).thenReturn(calculatorSvcResponse);

		this.mockMvc.perform(post("/calculatorservice/add").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(calculatorSvcRequest)))
				.andDo(print())
				.andExpect(status().isOk()).andDo(print());
	}


	/**
	 * converts a Java object into JSON representation
	 *  
	 * @param obj
	 * @return
	 */

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
