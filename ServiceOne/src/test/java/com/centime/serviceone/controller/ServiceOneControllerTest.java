package com.centime.serviceone.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.centime.serviceone.util.Constants;

@WebMvcTest(ServiceOneController.class)
@ExtendWith(MockitoExtension.class)
public class ServiceOneControllerTest {

    @InjectMocks
    private ServiceOneController serviceOneController;

    @Mock
    private RestTemplate restTemplate;

    private MockMvc mockMvc;
    
    @Mock
    private Constants constants;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(serviceOneController).build();
    }

    @Test
    public void testCheckHealth() throws Exception {
        mockMvc.perform(get("/serviceone/health"))
               .andExpect(status().isOk())
               .andExpect(content().string("Up"));
    }

    @Test
    public void testAggregate() throws Exception {
    	

        when(restTemplate.getForEntity(eq(constants.SERVICE_SECOND_HELLO_API_URL), eq(String.class)))
                .thenReturn(new ResponseEntity<>("Service2Response", HttpStatus.OK));
        when(restTemplate.postForEntity(eq(constants.SERVICE_THREE_CONCAT_API_URL), any(), eq(String.class)))
                .thenReturn(new ResponseEntity<>("Service3Response", HttpStatus.OK));

        mockMvc.perform(post("/serviceone/aggregate")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"name\":\"Abhishek\",\"surname\":\"Saxena\"}"))
               .andExpect(status().isOk())
               .andExpect(content().string("Service2Response Service3Response"));
    }

    @Test
    public void testAggregateWithInvalidJson() throws Exception {
        when(restTemplate.getForEntity(eq(constants.SERVICE_SECOND_HELLO_API_URL), eq(String.class)))
                .thenReturn(new ResponseEntity<>("Service2Response", HttpStatus.OK));

        when(restTemplate.postForEntity(eq(constants.SERVICE_THREE_CONCAT_API_URL), any(), eq(String.class)))
                .thenThrow(new RestClientException("Simulated RestClientException"));

        mockMvc.perform(post("/serviceone/aggregate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\",\"surname\":\"Doe\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{\"message\":\"Error communicating with external service\"}"));
    }


}

