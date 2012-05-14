/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.acminds.acuteauto.persistence.dto.Account;
import com.acminds.acuteauto.persistence.dto.Applicant;
import com.acminds.acuteauto.persistence.dto.Employment;
import com.acminds.acuteauto.persistence.dto.LoanApplication;
import com.acminds.acuteauto.persistence.dto.Location;
import com.acminds.acuteauto.persistence.dto.Residence;
import com.acminds.acuteauto.persistence.dto.TradeinInfo;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.Utils;

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
		loanApp.setUpdateDate(Utils.today());
		Applicant app = new Applicant();
		Residence res = new Residence();
		res.setLocation(new Location());		
		Employment emp = new Employment();
		emp.setCurrentEmployer(true);
		emp.setLocation(new Location());
		TradeinInfo tdInfo = new TradeinInfo();
		Account acc = new Account();
		acc.setLocation(new Location());
		app.getResidences().add(res);
		app.getEmployments().add(emp);
		app.getAccounts().add(acc);
		loanApp.getApplicants().add(app);
		loanApp.getTradeinInfos().add(tdInfo);
	}
	
	public String submitLoan() {
		return null;
	}
}
