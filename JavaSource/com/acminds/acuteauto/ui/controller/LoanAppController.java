/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.persistence.dto.Account;
import com.acminds.acuteauto.persistence.dto.Applicant;
import com.acminds.acuteauto.persistence.dto.Employment;
import com.acminds.acuteauto.persistence.dto.LoanApplication;
import com.acminds.acuteauto.persistence.dto.Location;
import com.acminds.acuteauto.persistence.dto.Residence;
import com.acminds.acuteauto.persistence.dto.TradeinInfo;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="lnCtrl")
@ViewScoped
public class LoanAppController extends InventoryController {
	private Log logger = LogFactory.getLog(LoanAppController.class);
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
		Account acc = new Account();
		acc.setLocation(new Location());
		app.getResidences().add(res);
		app.getEmployments().add(emp);
		app.getAccounts().add(acc);
		loanApp.getApplicants().add(app);
		loanApp.getTradeinInfos().add(new TradeinInfo());
	}
	
	public String submitLoan() {		
		try {
			loanApp.setVehicle(getCar());
			service.getBaseDao().save(loanApp, false);
			for(Applicant app:loanApp.getApplicants()) {
				service.getBaseDao().save(app, false);
				for(Residence r:app.getResidences()) {
					service.getBaseDao().save(r, false);
					service.getBaseDao().save(r.getLocation(), false);
				}
			}
			service.getBaseDao().saveAll(loanApp.getTradeinInfos(), false);
			service.getBaseDao().commit();
			init();
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "loanAppSuccess");
			logger.info("Loan Application submitted successfully.");
		} catch (Exception e) {
			logger.error("Loan Application Submit failed due to an internal error.", e);
			service.getBaseDao().rollback();
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return null;
	}
}
