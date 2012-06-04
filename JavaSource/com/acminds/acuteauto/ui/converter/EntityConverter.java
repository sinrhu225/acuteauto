/**
 * 
 */
package com.acminds.acuteauto.ui.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.BaseDTO;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@FacesConverter(value="entityConverter")
public class EntityConverter implements Converter {
	private Log logger = LogFactory.getLog(this.getClass());
	
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
			if(id == 0)
				return null;
			return BaseDAO.getInstance().get(getEntityName(arg1), id);
		} catch (ConverterException e) {
			logger.error("Exception while converting to an Entity: "+arg2, e);
			throw e;
		} catch (Exception e) {
			logger.error("Exception while converting to an Entity: "+arg2, e);
			throw new ConverterException(WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed"));
		}		
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(!Utils.isEmpty(arg2)) {
			if(arg2 instanceof BaseDTO)
				return String.valueOf(((BaseDTO)arg2).getId());
			else
				return String.valueOf(arg2);
		}
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
		throw new ConverterException(WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "Please add a param with name 'entityName' to the converter's parent component"));
	}

}
