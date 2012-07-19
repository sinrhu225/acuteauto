/**
 * 
 */
package com.acminds.acuteauto.service;

import com.acminds.acuteauto.persistence.dto.Client;
import com.acminds.acuteauto.persistence.dto.Image;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.utils.Constants;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
public class ClientService extends BaseService {

	public void deleteImage(Image image, Client dealer, UserInfo deletedBy) throws Exception {
		String location = Utils.getUserHome()+Constants.CLIENT_IMG_LOC+dealer.getClientId()+"/"+image.getName();
		try {
			trashFile(location, deletedBy, false);
			dealer.getImages().remove(image);
			delete(image, true);
		} catch(Exception e) {
			rollback();
			logger.error("Error deleting Vehicle", e);
			throw e;
		}
	}
}
