package com.acminds.acuteauto.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebUtils {
	private static String DEFAULT_RESOURCE_BUNDLE = "resources";
	
	public static <T> T findBean(String beanName, Class<T> beanClass) {
		if(getFacesContext() != null)
			return beanClass.cast(getFacesContext().getApplication()
	    		.evaluateExpressionGet(getFacesContext(), "#{" + beanName + "}", beanClass));
		else {
			Object obj = getRequest().getAttribute(beanName);
			if(obj == null)
				obj = getSession().getAttribute(beanName);
			return beanClass.cast(obj);
		}
	}
	
	public static void setBeanToScope(String name, Object value, boolean isSession) {
		if(!isSession)
			getRequest().setAttribute(name, value);
		else
			getSession().setAttribute(name, value);
	}
	
	private static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest)getFacesContext().getExternalContext().getRequest();
	}
	
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	public static FacesMessage addMessage(Severity severity, String key) {
		FacesMessage msg = new FacesMessage(severity, getValueForKey(key),
				"");
		getFacesContext().addMessage(null, msg);
		return msg;
	}
	
	public static String formatDisplayId(String name, int id) {
		name = name+"XXXXX";
		String prefix = name.substring(0, 4);
		String ids = String.valueOf(id);
		if(ids.length()<6)
			ids = "000000".substring(0, 6-ids.length())+ids;		
		return prefix+ids;
	}
	
	public static int retrieveId(String displayId) {
		return Integer.parseInt(displayId.substring(4));
	}
	
	public static boolean isWebRequest() {
		return getFacesContext()!=null;
	}
		
	private static String getValueForKey(String key) {
		String val = null;
		try {
			val = getResourceBundle().getString(key);
		} catch (MissingResourceException e) {
			val = "MISSING: " + key + " :MISSING";
		}
		return val;
	}
	
	private static ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE);
	}
	
	public static SelectItem getDefaultSelectItem(boolean isBlank, String value) {
		SelectItem si = null;
		if(isBlank) {
			si = new SelectItem(0, "");
		} else {
			if(Utils.isEmpty(value))
				si = new SelectItem(0, "Please Select");
			else
				si = new SelectItem(0, value);
		}
		si.setNoSelectionOption(true);
		return si;
	}
	
	public static void main(String[] args) {
		System.out.println(formatDisplayId("Mansur", 12).substring(4) +" of "+formatDisplayId("Mansur", 12));
		System.out.println(retrieveId(formatDisplayId("Mansur", 12)));
		
	}
}
