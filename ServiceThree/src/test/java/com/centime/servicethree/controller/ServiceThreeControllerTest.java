package com.centime.servicethree.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringJUnitWebConfig
@ExtendWith(MockitoExtension.class)
public class ServiceThreeControllerTest {

	@InjectMocks
	private ServiceThreeController serviceThreeController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(serviceThreeController).build();
	}

	@Test
	public void testConcatenateSuccess() throws Exception {

		String response = mockMvc
				.perform(post("/servicethree/concatenate").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Abhishek\",\"surname\":\"Saxena\"}"))
				.andExpect(status().isOk()).andExpect(content().string("Abhishek Saxena")).andReturn().getResponse()
				.getContentAsString();

		assertNotNull(response);
		assertEquals("Abhishek Saxena", response);
	}

	@Test
	public void testConcatenateFailure() throws Exception {
		String response = mockMvc
				.perform(post("/servicethree/concatenate").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":null,\"surname\":\"Saxena\"}"))
				.andExpect(status().isBadRequest()).andExpect(content().string("Name and surname must not be null"))
				.andReturn().getResponse().getContentAsString();

		assertNotNull(response);
		assertEquals("Name and surname must not be null", response);
	}
}
