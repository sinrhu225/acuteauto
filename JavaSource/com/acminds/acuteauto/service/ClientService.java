/**
 * 
 */
package com.acminds.acuteauto.service;

import java.util.List;

import com.acminds.acuteauto.persistence.dto.Client;
import com.acminds.acuteauto.persistence.dto.Image;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.utils.Constants;
import com.acminds.acuteauto.utils.EnumConstants.ImageType;
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
			logger.error("Error deleting Image", e);
			throw e;
		}
	}
	
	public void updateClientProfile(List<Image> images, Client dealer) throws Exception {
		try {
			if(!Utils.isEmpty(images)) {
				Image image = images.get(0);
				if(!image.isPersistent()) {
					image.setClient(dealer);
					image.setImageType(ImageType.LOGO);
					dealer.getImages().clear();
					dealer.getImages().add(image);
				}
				if(!Utils.isEmpty(image.getImageData())) {
					String location = Utils.getUserHome()+Constants.CLIENT_IMG_LOC+dealer.getClientId()+"/"+image.getName();
					Utils.writeImage(location, image.getImageData());
					logger.info("Image "+image.getName()+" written to the following location: "+location);		
					image.setImageLocation(Constants.CLIENT_IMG_LOC+dealer.getClientId()+"/"+image.getName());
				}
			}
			saveOrUpdate(dealer, true);
			logger.info("Dealer Profile updated successfully.");			
		} catch (Exception e) {
			rollback();
			logger.error("Error Updating Dealer Profile", e);
			throw e;
		}
	}
}
