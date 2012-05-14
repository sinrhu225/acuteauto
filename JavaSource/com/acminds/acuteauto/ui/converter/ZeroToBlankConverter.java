/**
 * 
 */
package com.acminds.acuteauto.ui.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Mansur
 *
 */
@FacesConverter(value="zeroToBlankConverter")
public class ZeroToBlankConverter implements Converter {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return arg2;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2 != null) {
			if(arg2 instanceof Number) {
				if(((Number)arg2).intValue()==0 
						|| ((Number)arg2).doubleValue()==0 
						|| ((Number)arg2).longValue()==0) 
					return "";
			}		
			return arg2.toString();
		}
		return null;
	}

}
