package com.centime.serviceone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.centime.serviceone.customexception.ErrorResponse;
import com.centime.serviceone.model.JsonPayload;
import com.centime.serviceone.util.Constants;

@RestController
@RequestMapping("/serviceone")
public class ServiceOneController {
	
	@Autowired
	Constants constants;
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceOneController.class);

	@GetMapping("/health")
	public String checkHealth() {
		return "Up";
	}

	@PostMapping("/aggregate")
	public String aggregate(@RequestBody JsonPayload jsonPayload) {
		logger.info("Service One aggregate method Start");
		
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Service two hello api call");
		ResponseEntity<String> service2Response = restTemplate.getForEntity(constants.SERVICE_SECOND_HELLO_API_URL,
				String.class);
		logger.info("Service three concate api call");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<JsonPayload> requestEntity = new HttpEntity<>(jsonPayload, headers);

		ResponseEntity<String> service3Response = restTemplate.postForEntity(constants.SERVICE_THREE_CONCAT_API_URL,
				requestEntity, String.class);
		
		String concatenatedResponse = service2Response.getBody() + " " + service3Response.getBody();
		return concatenatedResponse;
	}

	@ExceptionHandler(JsonParseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleJsonParseException(JsonParseException ex) {
		return new ErrorResponse("Invalid JSON payload", HttpStatus.BAD_REQUEST.value());
	}
}
