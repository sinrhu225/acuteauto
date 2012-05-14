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
public class LoanAppController extends InventoryController {
	
	private LoanApplication loanApp;
	private boolean acceptTerms;
	public LoanApplication getLoanApp() {
		return loanApp;
	}
	public void setLoanApp(LoanApplication loanApp) {
		this.loanApp = loanApp;
	}
	public boolean isAcceptTerms() {
		return acceptTerms;
	}
	public void setAcceptTerms(boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
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
		service.getBaseDao().save(loanApp, false);
		service.getBaseDao().saveAll(loanApp.getApplicants(), false);
		service.getBaseDao().saveAll(loanApp.getTradeinInfos(), false);
		service.getBaseDao().save(loanApp, false);
		service.getBaseDao().save(loanApp, false);
		return null;
	}
}
