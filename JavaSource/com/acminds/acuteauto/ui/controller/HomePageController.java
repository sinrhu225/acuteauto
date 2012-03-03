/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.ui.form.CarSearchForm;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="hmpCtrl")
@RequestScoped
public class HomePageController extends BaseController{
	
	private CarSearchForm csForm = new CarSearchForm();
	public CarSearchForm getCsForm() {
		return csForm;
	}
	public void setCsForm(CarSearchForm csForm) {
		this.csForm = csForm;
	}
	
	public String searchCars() {
		return null;
	}
}
