/**
 * 
 */
package com.acminds.acuteauto.ui.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.dto.Enum;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
@FacesConverter(value="enumConverter")
public class EnumConverter implements Converter {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(!Utils.isEmpty(arg2))
			return Integer.parseInt(arg2);
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		BaseDAO dao = new BaseDAO();
		if(Utils.isEmpty(arg2))
			return "";
		Enum e = dao.get(Enum.class, (Integer)arg2);
		if(e == null)
			return "";
		String val = e.getEnumValue();
		return val;
	}

}
