/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	
	public String submitInquiry() {
		FindVehicle fv = inquiry.getFindVehicles().get(0);
		fv.setModel(service.getBaseDao().get(Model.class, getModelId()));
		service.getBaseDao().save(inquiry, false);
		service.getBaseDao().saveAll(inquiry.getFindVehicles(), true);
		WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "inqSuccess");
		init();
		return null;
	}
}
