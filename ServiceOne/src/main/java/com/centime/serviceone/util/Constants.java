package com.centime.serviceone.util;

import org.springframework.stereotype.Component;

@Component
public class Constants {

	public final String SERVICE_SECOND_HELLO_API_URL = "http://localhost:8092/servicetwo/hello";
	public final String SERVICE_THREE_CONCAT_API_URL = "http://localhost:8090/servicethree/concatenate";
}
