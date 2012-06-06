/**
 * 
 */
package com.acminds.acuteauto.persistence;

import java.lang.reflect.Method;

import javax.persistence.Id;

/**
 * @author MANSUR
 *
 */
public class BaseDTO {
	
	public Integer getId() {
		for(Method m:getClass().getMethods()) {
			if(m.isAnnotationPresent(Id.class)) {
				try {
					return (Integer)m.invoke(this, new Object[]{});
				} catch (Exception e) {
					return null;
				}
			}
		}
		return null;
	}
	
	public boolean isPersistent() {
		return (getId()!=null && getId()>0);
	}
	
	/**
	 * To overcome the JSF Validation Error (Value is not valid).
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof BaseDTO)) {
			return false;
		}
		return (this.getId() == ((BaseDTO)obj).getId());
	}

}
