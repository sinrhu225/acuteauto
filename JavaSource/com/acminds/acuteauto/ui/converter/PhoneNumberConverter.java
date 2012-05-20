/**
 * 
 */
package com.acminds.acuteauto.ui.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
@FacesConverter(value="phoneConverter")
public class PhoneNumberConverter implements Converter {
	private static String EXT = " Ext:";
	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(Utils.isEmpty(arg2))
			return null;
		String ph = arg2.replace("(", "").replace(") ", "").replace(" - ", "").replace(EXT, "");
		return ph;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(!Utils.isEmpty(arg2)) {
			StringBuilder sb = new StringBuilder();
			String p[] = Utils.slicePhoneNumber(arg2.toString());
			sb.append("(").append(p[0]).append(") ")
			.append(p[1]).append(" - ")
			.append(p[2]);
			if(p.length > 3)
				sb.append(EXT).append(p[3]);
			return sb.toString();
		}
		return null;
	}

}
