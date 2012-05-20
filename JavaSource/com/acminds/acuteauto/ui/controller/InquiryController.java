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

import com.acminds.acuteauto.persistence.dto.FindVehicle;
import com.acminds.acuteauto.persistence.dto.Inquiry;
import com.acminds.acuteauto.persistence.dto.Model;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="inqCtrl")
@ViewScoped
public class InquiryController extends InventoryController {
	private Log logger = LogFactory.getLog(InquiryController.class);
	private Inquiry inquiry;
	public Inquiry getInquiry() {
		return inquiry;
	}
	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	@PostConstruct
	public void init() {
		inquiry = new Inquiry();
		FindVehicle fv = new FindVehicle();
		fv.setMileage(50000);
		fv.setInquiry(inquiry);
		inquiry.getFindVehicles().add(fv);
	}
	
	public String findVehicle() {
		try {
			FindVehicle fv = inquiry.getFindVehicles().get(0);
			fv.setModel(service.getBaseDao().get(Model.class, getModelId()));
			service.getBaseDao().save(inquiry, false);
			service.getBaseDao().saveAll(inquiry.getFindVehicles(), true);
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "findVehSuccess");
			init();
			logger.info("Find Vehicle Inquiry submitted successfully.");
		} catch (Exception e) {
			logger.error("Find Vehicle Inquiry failed due to an internal error.", e);
			service.getBaseDao().rollback();
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "submitFailed");
		}
		return null;
	}
	
	public String submitInquiry() {
		try {
			inquiry.getFindVehicles().clear();
			inquiry.setVehicle(getCar());
			service.getBaseDao().save(inquiry, true);
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "inqSuccess");
			init();
			logger.info("Inquiry submitted successfully.");
		} catch (Exception e) {
			logger.error("Inquiry failed due to an internal error.", e);
			service.getBaseDao().rollback();
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "submitFailed");
		}
		return "/pub/inv/invList";
	}
}
