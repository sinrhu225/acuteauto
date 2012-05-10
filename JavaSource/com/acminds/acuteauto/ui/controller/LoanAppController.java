/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.acminds.acuteauto.persistence.dto.LoanApplication;
import com.acminds.acuteauto.persistence.dto.Location;
import com.acminds.acuteauto.persistence.dto.UserInfo;
import com.acminds.acuteauto.ui.BaseController;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="lnCtrl")
@ViewScoped
public class LoanAppController extends BaseController {
	
	private LoanApplication loanApp;
	public LoanApplication getLoanApp() {
		return loanApp;
	}
	public void setLoanApp(LoanApplication loanApp) {
		this.loanApp = loanApp;
	}

	@PostConstruct
	public void init() {
		loanApp = new LoanApplication();
		loanApp.setUserInfoByApplicant(new UserInfo());
		loanApp.getUserInfoByApplicant().getLocations().add(new Location());
	}
	
	public String submitLoan() {
		return null;
	}
}
