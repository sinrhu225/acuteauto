/**
 * 
 */
package com.acminds.acuteauto.service;

import com.acminds.acuteauto.exceptions.AcuteAutoApplicationException;
import com.acminds.acuteauto.persistence.dto.UserInfo;

/**
 * @author Mansur
 *
 */
public class UserInfoService extends BaseService {
	
	public void saveOrUpdateUser(UserInfo user) throws AcuteAutoApplicationException {
		try {
		baseDao.saveOrUpdate(user, false);
		baseDao.saveOrUpdateAll(user.getLocations(), false);
		baseDao.saveOrUpdateAll(user.getImages(), false);		
		baseDao.commit();
		} catch (Exception e) {
			baseDao.rollback();
			logger.error("Exception occured while trying to save UserInfo", e);
			throw new AcuteAutoApplicationException(e);
		}
	}
	
	public void deleteUser(UserInfo user) throws AcuteAutoApplicationException {
		try {
		baseDao.deleteAll(user.getLocations(), false);
		baseDao.deleteAll(user.getImages(), false);		
		baseDao.delete(user, false);
		baseDao.commit();
		} catch (Exception e) {
			baseDao.rollback();
			logger.error("Exception occured while trying to delete UserInfo", e);
			throw new AcuteAutoApplicationException(e);
		}
	}

}
