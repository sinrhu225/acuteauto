/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import com.acminds.acuteauto.persistence.dto.Image;
import com.acminds.acuteauto.persistence.dto.Location;
import com.acminds.acuteauto.persistence.dto.Role;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.service.UserInfoService;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.Constants;
import com.acminds.acuteauto.utils.EnumConstants.ImageType;
import com.acminds.acuteauto.utils.EnumConstants.LocationType;
import com.acminds.acuteauto.utils.EnumConstants.UserStatus;
import com.acminds.acuteauto.utils.EnumConstants.UserType;
import com.acminds.acuteauto.utils.ImageUtils;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="uiCtrl")
@ViewScoped
public class UserInfoController extends BaseController {

	private List<UserInfo> users;
	private List<SelectItem> roles;
	private UserInfo user;
	private UserInfoService service = new UserInfoService();
	
	public List<SelectItem> getRoles() {
		if(roles == null) {
			roles = new ArrayList<SelectItem>();
			roles.add(WebUtils.getDefaultSelectItem("Please Select"));
			List<Role> list = baseService.createNamedQuery("getRoles", Role.class).getResultList();
			for(Role r:list)
				roles.add(new SelectItem(r, r.getRoleName()));
		}
		return roles;
	}
	
	public List<UserInfo> getUsers() {
		if(Utils.isEmpty(users))
			users = service.getAllUsers();
		return users;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		if(user == null && (this.user!=null && this.user.getUserInfoId()==null))
			;// do nothing.		
		else
			this.user = user;
	}
	
	public void preRenderUserEdit(ComponentSystemEvent event) {
		if(user == null) {
			user = new UserInfo();
			Location l = new Location();
			l.setLocationType(LocationType.PRIMARY);
			user.getLocations().add(l);
			user.setClient(authorizedUser.getClient());
			user.setUserType(UserType.CLIENT);
			user.setStatus(UserStatus.ACTIVE);
		}
	}
	
	public String saveUser() {
		try {
			logger.info("Saving User.");
			service.saveOrUpdateUser(user);
			discardNewImage();
			logger.info("User saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveUserSuccessful");
			return "/sec/usm/userList";
		} catch (Exception e) {
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
			return null;
		}
		
	}
	
	public String deleteUser() {
		try {
			logger.info("Deleting User.");
			service.deleteUser(user);
			logger.info("User deleted successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delUserSuccessful");
		} catch (Exception e) {
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "deleteFailed");
		}
		return "/sec/usm/userList";
	}
	
	public void uploadPhoto(FileUploadEvent event) throws Exception{
		UploadedFile item = event.getUploadedFile();
		if(Utils.isEmpty(user.getImages())) {
			Image i = new Image();
			i.setImageType(ImageType.PRIMARY);
			i.setImageLocation("temp");
			user.getImages().add(i);
		}
		Image file = user.getDisplayImage();
        file.setName(item.getName());
        byte[] data = ImageUtils.reScaleTrilinear(item.getData(), 200, 200);
        file.setImageData(data); 
        file.setImageSize(data.length);
        WebUtils.setBeanToScope(Constants.IMG_HOLDER, file, true);
	}	
	
	public String discardNewImage(){
		user.getDisplayImage().setImageData(null);
		WebUtils.getSession().removeAttribute(Constants.IMG_HOLDER);
		return null;
	}
}
