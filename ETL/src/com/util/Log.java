package com.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log {

	private static Logger logger = Logger.getLogger(Log.class);
	
	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public static void debug(String debugInfo){
		logger.debug(debugInfo);
	}
	
	public static void info(String Info){
		logger.info(Info);
	}
	
	public static void error(String errInfo){
		logger.error(errInfo);
	}
	
	public static void main(String argsp[]){
		Log.debug("this is debug info");
		Log.info("this is info message");
		Log.error("this is error info");
	}
}
