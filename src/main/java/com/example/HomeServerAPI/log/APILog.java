package com.example.HomeServerAPI.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APILog {

	private static final Logger log = LoggerFactory.getLogger("logger");
	
	public static void info(String message) {
		log.info(message);
	}
	public static void info(String message, Object obj) {
		log.info(message,obj);
	}
	
	public static void error(String message) {
		log.error(message);
	}

	public static void warn(String message) {
		log.warn(message);
	}
	
	
	
}
