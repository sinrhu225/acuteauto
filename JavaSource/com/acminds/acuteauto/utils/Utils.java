/**
 * 
 */
package com.acminds.acuteauto.utils;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.acminds.acuteauto.exceptions.AcuteAutoRuntimeException;

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
	
	public static Date toDate(String value) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.parse(value);
	}
	
	public static String getUserHome() {
		return System.getProperty("user.home");
	}
	
	public static InputStream getResource(String name) throws ClassNotFoundException {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}
	
	public static String[] slicePhoneNumber(String phoneNumber) {
		if(isEmpty(phoneNumber))
			return null;
		if(phoneNumber.length() < 10)
			throw new AcuteAutoRuntimeException("Invalid Phone Number");
		String s[] = null;
		if(phoneNumber.length() == 10) {
			s = new String[3];
			s[0] = phoneNumber.substring(0, 3);
			s[1] = phoneNumber.substring(3, 6);
			s[2] = phoneNumber.substring(6);
		} else {
			s = new String[4];
			s[0] = phoneNumber.substring(0, 3);
			s[1] = phoneNumber.substring(3, 6);
			s[2] = phoneNumber.substring(6, 10);
			s[3] = phoneNumber.substring(10);
		}
		return s;
	}

}
