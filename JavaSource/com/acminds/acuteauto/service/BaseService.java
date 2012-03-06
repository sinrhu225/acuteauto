/**
 * 
 */
package com.acminds.acuteauto.service;

import com.acminds.acuteauto.persistence.BaseDAO;

/**
 * @author Mansur
 *
 */
public class BaseService {
	private BaseDAO baseDao = new BaseDAO();
	public BaseDAO getBaseDao() {
		return baseDao;
	}
	

}
