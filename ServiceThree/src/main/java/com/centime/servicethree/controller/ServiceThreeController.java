package com.centime.servicethree.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.centime.servicethree.model.JsonPayload;

@RestController
@RequestMapping("/servicethree")
public class ServiceThreeController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceThreeController.class);

    @PostMapping("/concatenate")
    public ResponseEntity<String> concatenate(@RequestBody JsonPayload jsonPayload) {
        logger.info("Service Three Concatenate Method Start");
        logger.info("Json : Name= {} , Surname : {} ", jsonPayload.getName(), jsonPayload.getSurname());
        
        if (jsonPayload.getName() == null || jsonPayload.getSurname() == null) {
            throw new IllegalArgumentException("Name and surname must not be null");
        }

        String fullName = jsonPayload.getName() + " " + jsonPayload.getSurname();
        
        logger.info("Service Three Concatenate Method End");
        return ResponseEntity.ok(fullName);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
