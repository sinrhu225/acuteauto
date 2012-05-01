/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.acminds.acuteauto.persistence.dto.LoanApplication;
import com.acminds.acuteauto.ui.BaseController;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="lnCtrl")
@ViewScoped
public class LoanAppController extends BaseController {
	
	@Resource(name="loanApplication")
	private LoanApplication loanApp;
	public LoanApplication getLoanApp() {
		return loanApp;
	}
	public void setLoanApp(LoanApplication loanApp) {
		this.loanApp = loanApp;
	}

	@PostConstruct
	public String createLoan() {
		
		return null;
	}
}
