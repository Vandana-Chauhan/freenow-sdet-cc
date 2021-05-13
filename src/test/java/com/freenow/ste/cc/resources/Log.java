package com.freenow.ste.cc.resources;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class Log {
	
private static Logger Log =	Logger.getLogger(Log.class.getName());




	

 public static void info(String message) {
	 
	 DOMConfigurator.configure("log4j.xml"); 
	 
		Log.info(message);

		}



 public static void error(String message) {

    Log.error(message);

	}



 public static void debug(String message) {

    Log.debug(message);

	}

}
 

