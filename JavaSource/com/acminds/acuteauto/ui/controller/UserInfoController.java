/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.acminds.acuteauto.exceptions.AcuteAutoApplicationException;
import com.acminds.acuteauto.persistence.dto.Location;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.service.UserInfoService;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.EnumConstants.UserType;
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
		return users;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	@PostConstruct
	public void init() {
		user = new UserInfo();
		user.getLocations().add(new Location());
		user.setClient(authorizedUser.getClient());
		user.setUserType(UserType.CLIENT);
	}
	
	public String addNewUser() {
		init();
		return null;
	}
	
	public String saveUser() {
		try {
			service.saveOrUpdateUser(user);
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveUserSuccessful");
		} catch (AcuteAutoApplicationException e) {
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "saveUserFailed");
		}
		return null;
	}
	
	public String deleteUser() {
		try {
			service.deleteUser(user);
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delUserSuccessful");
		} catch (AcuteAutoApplicationException e) {
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "delUserFailed");
		}
		return null;
	}
}
