/**
 * 
 */
package com.acminds.acuteauto;

import java.util.Properties;

import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
public class ConfigManager {
	private static final String APP_CONFIG = "cfg/App-Config.properties";
	private static Properties props = new Properties();
	static {
		try {
			props.load(Utils.getResource(APP_CONFIG));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key) {
		return props.getProperty(key);
	}
}
