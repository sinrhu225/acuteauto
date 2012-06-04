/**
 * 
 */
package com.acminds.acuteauto.service;

import java.util.List;

import com.acminds.acuteauto.exceptions.AcuteAutoApplicationException;
import com.acminds.acuteauto.persistence.dto.Image;
import com.acminds.acuteauto.persistence.dto.Location;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.utils.Constants;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
public class UserInfoService extends BaseService {
	
	public void saveOrUpdateUser(UserInfo user) throws AcuteAutoApplicationException {
		try {
			Image img = user.getDisplayImage(); 
			if(user.isPersistent()) {
				saveOrUpdate(user, false);
				saveOrUpdateAll(user.getLocations(), false);
				if(img.getImageData()!=null)
					Utils.writeImage(img.getRealLocation(), img.getImageData());
				saveOrUpdate(img, true);
			} else {
				for(Location l:user.getLocations())
					l.setUserInfo(user);
				for(Image i:user.getImages())
					i.setUserInfo(user);
				saveOrUpdate(user, true);
				refresh(user);
				String location = Utils.getUserHome()+Constants.USER_IMG_LOC+user.getUserInfoId()+"/"+img.getName();
				Utils.writeImage(location, img.getImageData());
				img.setImageLocation(Constants.USER_IMG_LOC+user.getUserInfoId()+"/"+img.getName());
				saveOrUpdate(img, true);
			}			
		} catch (Exception e) {
			rollback();
			logger.error("Exception occured while trying to save UserInfo", e);
			throw new AcuteAutoApplicationException(e);
		}
	}
	
	public void deleteUser(UserInfo user) throws AcuteAutoApplicationException {
		try {
			Image img = user.getDisplayImage();
			deleteAll(user.getLocations(), false);
			deleteAll(user.getImages(), false);		
			delete(user, false);
			if(img!=null && !Utils.isEmpty(img.getImageLocation())) {
				Utils.deleteFile(img.getRealLocation());
				Utils.deleteFile(Utils.getUserHome()+Constants.USER_IMG_LOC+user.getUserInfoId());
			}
			commit();			
		} catch (Exception e) {
			rollback();
			logger.error("Exception occured while trying to delete UserInfo", e);
			throw new AcuteAutoApplicationException(e);
		}
	}
	
	public List<UserInfo> getAllUsers() {
		return createNamedQuery("getUsers", UserInfo.class).getResultList();
	}

}
