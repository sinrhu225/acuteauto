/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ViewScoped
@ManagedBean(name="loginCtrl")
public class LoginController extends BaseController {

	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		List<UserInfo> list = baseService.createNamedQuery("loginUser", UserInfo.class)
									.setParameter("userName", userName)
									.setParameter("pass", password)
									.getResultList();
		if(Utils.isEmpty(list)) {
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "invalidLogin");
			return null;
		}
		WebUtils.setBeanToScope("authorizedUser", list.get(0), true);
		return "/sec/adminConsole?faces-redirect=true";
	}
	
	public String logout() {
		WebUtils.getSession().invalidate();
		return "/home?faces-redirect=true";
	}
}
