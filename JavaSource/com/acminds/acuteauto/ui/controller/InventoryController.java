/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.RandomStringUtils;

import com.acminds.acuteauto.persistence.dto.FeatureGroup;
import com.acminds.acuteauto.persistence.dto.Make;
import com.acminds.acuteauto.persistence.dto.Model;
import com.acminds.acuteauto.persistence.dto.Style;
import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.service.InventoryService;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.EnumConstants.TransmissionType;
import com.acminds.acuteauto.utils.EnumConstants.VehicleCondition;
import com.acminds.acuteauto.utils.EnumConstants.VehicleStatus;
import com.acminds.acuteauto.utils.EnumConstants.WarrantyType;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="invCtrl")
@ViewScoped
public class InventoryController extends BaseController {
	protected InventoryService service = new InventoryService();
	private String backTo;
	private Vehicle car;
	private int makeId;
	private int modelId;
	private int styleId;
	private int bodyType;
	private int year;
	private int price;
	private int mileage;
	private List<Vehicle> cars;
	private List<Make> allMakes;
	private List<SelectItem> makes = new ArrayList<SelectItem>();
	private List<SelectItem> models = new ArrayList<SelectItem>();
	private List<SelectItem> styles = new ArrayList<SelectItem>();
	private List<SelectItem> years = new ArrayList<SelectItem>();
	private List<SelectItem> prices = new ArrayList<SelectItem>();
	private List<FeatureGroup> allFeatureGroups;
	
	public Vehicle getCar() {
		return car;
	}	
	public void setCar(Vehicle car) {
		if(car == null && (this.car!=null && this.car.getVehicleId()==null))
			;
		else
			this.car = car;
	}
	public List<Vehicle> getCars() {
		cars = service.getCars(makeId, modelId, styleId, year, price, mileage, bodyType);
		return cars;
	}
	
	public String getBackTo() {
		return backTo;
	}
	public void setBackTo(String backTo) {
		this.backTo = backTo;
	}
	public int getStyleId() {
		return styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	public int getBodyType() {
		return bodyType;
	}
	public void setBodyType(int bodyType) {
		this.bodyType = bodyType;
	}

	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
		if(makeId==0) {
			setModelId(makeId);
			setStyleId(makeId);
		}
	}

	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	public List<Make> getAllMakes() {
		if(Utils.isEmpty(allMakes)) {
			allMakes = service.getMakes(0);			
		}
		return allMakes;
	}
	
	public List<SelectItem> getMakes() {
		if(Utils.isEmpty(makes)) {
			makes.add(WebUtils.getDefaultSelectItem("All Makes"));
			for(Make m: getAllMakes()) {
				makes.add(new SelectItem(m.getMakeId(), m.getName()));
			}
		}
		return makes;
	}
	
	public List<SelectItem> getModels() {
		models.clear();
		models.add(WebUtils.getDefaultSelectItem("All Models"));
		if(makeId > 0) {
			for(Make m: getAllMakes()) {
				if(makeId == m.getMakeId()) {
					for(Model md:m.getModels())
						models.add(new SelectItem(md.getModelId(), md.getName()));
				}
			}
		} 
		return models;
	}
	
	public List<SelectItem> getStyles() {
		styles.clear();
		styles.add(WebUtils.getDefaultSelectItem("All Styles"));
		if(modelId > 0) {
			for(Make m: getAllMakes()) {
				if(makeId==m.getMakeId()) {
					for(Model md:m.getModels()) {
						if(modelId==md.getModelId()) {
							for(Style st:md.getStyles())
								styles.add(new SelectItem(st.getStyleId(), st.getName()));
						}
					}
				}
			}
		}
		return styles;
	}
	
	public List<SelectItem> getYears() {
		if(Utils.isEmpty(years)) {
			years.add(WebUtils.getDefaultSelectItem("All Years"));
			for(int i= 2012; i>1991; i--)
				years.add(new SelectItem(i, String.valueOf(i)));
		}
		return years;
	}
	
	public List<SelectItem> getPrices() {
		if(Utils.isEmpty(prices)) {
			prices.add(new SelectItem(0, "No Max Price"));	
			for(int i= 3000; i<=15000; i=i+3000) {
				prices.add(new SelectItem(i, String.valueOf(i)));
			}
			for(int i= 20000; i<=50000; i=i+5000) {
				prices.add(new SelectItem(i, String.valueOf(i)));
			}
		}
		return prices;
	}
	
	public List<FeatureGroup> getAllFeatureGroups() {
		if(Utils.isEmpty(allFeatureGroups)) {
			allFeatureGroups = service.createNamedQuery("getFeatureGrpByName", FeatureGroup.class).setParameter("name", "OPTIONS").getSingleResult().getFeatureGroups();
		}
		return allFeatureGroups;
	}
	
	/***************************
	 ********PUB LISTENER METHODS******** 
	 ***************************/
	public void addVehicle(ComponentSystemEvent event) {
		if(car == null) {
			car = new Vehicle();
			car.setStockNbr(RandomStringUtils.randomAlphanumeric(7));
			car.setStatus(VehicleStatus.AVAILABLE);
			car.setVehCondition(VehicleCondition.USED);
			car.setWarrantyType(WarrantyType.NO_WARRANTY);
			car.setTransType(TransmissionType.AUTOMATIC);
		}
	}
	
	public void selectGroup(Integer groupId) {
		for(FeatureGroup fg:getAllFeatureGroups()) {
			if(fg.getFeatureGroupId() == groupId) {
				if(fg.isSelected())
					car.setSelectedFeatures(fg.getFeatures());
				else
					car.getSelectedFeatures().removeAll(fg.getFeatures());
			}
		}
	}
	/***************************
	 ********PUB ACTION METHODS******** 
	 ***************************/

	public String searchCars() {
		cars = service.getCars(makeId, modelId, styleId, year, price, mileage, bodyType);
		return "/pub/inv/invList";
	}
	
	public String submitVehicleInfo() {
		try {
			logger.info("Saving Vehicle.");
			service.saveOrUpdateCar(car);
			logger.info("Vehicle Saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveCarSuccessful");
		} catch(Exception e) {
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return null;
	}
	
	public String saveImages() {
		return null;
	}
	
	public String deleteVehicle() {
		return null;
	}
		
}
