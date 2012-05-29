/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.acminds.acuteauto.persistence.dto.Location;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.service.UserInfoService;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.EnumConstants.UserType;
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
	private UserInfo user;
	private UserInfoService service = new UserInfoService();
	
	public List<UserInfo> getUsers() {
		if(Utils.isEmpty(users))
			users = service.createNamedQuery("getUsers", UserInfo.class).getResultList();
		return users;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	public String addNewUser() {
		user = new UserInfo();
		user.getLocations().add(new Location());
		user.setClient(authorizedUser.getClient());
		user.setUserType(UserType.CLIENT);
		return "/sec/usm/userEdit";
	}
	
	public String viewPersonalProfile() {
		user = getAuthorizedUser();
		return "/sec/usm/userEdit";
	}
	
	public String saveUser() {
		try {
			logger.info("Saving User.");
			service.saveOrUpdateUser(user);
			logger.info("User saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveUserSuccessful");
		} catch (Exception e) {
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "saveUserFailed");
		}
		return "/sec/usm/userList";
	}
	
	public String deleteUser() {
		try {
			logger.info("Deleting User.");
			service.deleteUser(user);
			logger.info("User deleted successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delUserSuccessful");
		} catch (Exception e) {
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "delUserFailed");
		}
		return "/sec/usm/userList";
	}
}
