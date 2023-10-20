package com.centime.servicethree.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centime.servicethree.model.JsonPayload;

@RestController
@RequestMapping("/servicethree")
public class ServiceThreeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceThreeController.class);
	
	   @PostMapping("/concatenate")
	    public String concatenate(@RequestBody JsonPayload jsonPayload) {
		    logger.info("Service Three Concatenate Method Start");
		    logger.info("Json : Name= {} , Surname : {} ", jsonPayload.getName(), jsonPayload.getSurname());
	        String fullName = jsonPayload.getName() + " " + jsonPayload.getSurname();
	        logger.info("Service Three Concatenate Method End");
	        return fullName;
	    }
}
