/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.acminds.acuteauto.service.InventoryService;
import com.acminds.acuteauto.ui.BaseController;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="invCtrl")
@RequestScoped
public class InventoryController extends BaseController {
	private InventoryService service = new InventoryService();
	private String makeId;
	private String modelId;
	private String year;
	private String price;
	private String mileage;
	
	public String getMakeId() {
		return makeId;
	}

	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	
	/***************************
	 ********PUB METHODS******** 
	 ***************************/

	public String searchCars() {
		return null;
	}
	
	
	
	
}
