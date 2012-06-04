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

}
