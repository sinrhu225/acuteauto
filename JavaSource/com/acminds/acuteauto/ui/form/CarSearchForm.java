/**
 * 
 */
package com.acminds.acuteauto.ui.form;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Mansur
 *
 */
public class CarSearchForm {
	
	private String makeId;
	private String modelId;
	private String year;
	private String price;
	private String mileage;
	private List<SelectItem> makes;
	private List<SelectItem> models;
	private List<SelectItem> years;
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
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
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	
	public List<SelectItem> getMakes() {
		return makes;
	}
	public List<SelectItem> getModels() {
		return models;
	}
	public List<SelectItem> getYears() {
		return years;
	}		
}
