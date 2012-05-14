/**
 * 
 */
package com.acminds.acuteauto.ui.converter;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
@FacesConverter(value="entityConverter")
public class EntityConverter implements Converter {
	private String PARAM_NAME = "entityName";
	private String PARAM_VAL_PREFIX = "com.acminds.acuteauto.persistence.dto.";

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Integer id = null;
		if(Utils.isEmpty(arg2))
			return null;
		try {
			id = Integer.parseInt(arg2);
			return BaseDAO.getInstance().get(getEntityName(arg1), id);
		} catch (Exception e) {
			return null;
		}		
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(!Utils.isEmpty(arg2))
			return arg2.toString();
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	private Class getEntityName(UIComponent arg1) throws ClassNotFoundException {
		String paramName, paramValue = null;
		for(UIComponent comp:arg1.getChildren()) {
			if(comp instanceof UIParameter) {
				paramName = ((UIParameter)comp).getName();
				if(PARAM_NAME.equalsIgnoreCase(paramName)) {
					paramValue = ((UIParameter)comp).getValue().toString();
					return Class.forName(PARAM_VAL_PREFIX+paramValue);
				}
			}
		}
		return null;
	}

}
