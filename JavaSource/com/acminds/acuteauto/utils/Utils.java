/**
 * 
 */
package com.acminds.acuteauto.utils;

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

}
