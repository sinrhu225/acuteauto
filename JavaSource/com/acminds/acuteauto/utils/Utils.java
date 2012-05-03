/**
 * 
 */
package com.acminds.acuteauto.utils;

import java.io.InputStream;
import java.util.Collection;
import java.util.Date;

/**
 * @author MANSUR
 *
 */
public class Utils {
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object value) {
		if (value == null)
			return true;
		if (value instanceof String) {
			return (((String) value).trim().length() == 0);
		}
		if (value instanceof Collection) {
			return (((Collection) value).size() == 0);
		}
		return false;
	}
	
	public static Date today() {
		return new Date();
	}
	
	public static String getUserHome() {
		return System.getProperty("user.home");
	}
	
	public static InputStream getResource(String name) throws ClassNotFoundException {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}

}
