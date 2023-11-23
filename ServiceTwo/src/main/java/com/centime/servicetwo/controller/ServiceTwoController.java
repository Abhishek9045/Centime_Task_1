package com.centime.servicetwo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicetwo")
public class ServiceTwoController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceTwoController.class);

	@GetMapping("/hello")
	public ResponseEntity<String> getHello() {
		logger.info("Start Service Two Hello API");
		logger.info("End Service Two Hello API");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>("Hello", headers, HttpStatus.OK);
	}
}
