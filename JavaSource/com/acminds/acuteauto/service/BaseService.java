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
	private BaseDAO baseDao = BaseDAO.getInstance();
	public BaseDAO getBaseDao() {
		return baseDao;
	}
	

}
