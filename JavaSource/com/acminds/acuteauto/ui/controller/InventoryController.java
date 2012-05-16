/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.acminds.acuteauto.persistence.dto.Make;
import com.acminds.acuteauto.persistence.dto.Model;
import com.acminds.acuteauto.persistence.dto.Style;
import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.service.InventoryService;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="invCtrl")
@SessionScoped
public class InventoryController extends BaseController {
	protected InventoryService service = new InventoryService();
	private Integer carId;
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
	
	public Vehicle getCar() {
		if(carId!=null) {
			if(car!=null && car.getVehicleId()==carId)
				return car;
			car = service.getDao().get(Vehicle.class, carId);
			return car;
		}
		return null;
	}
	
	public List<Vehicle> getCars() {
		cars = service.getCars(makeId, modelId, styleId, year, price, mileage, bodyType);
		return cars;
	}
	
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
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
	/***************************
	 ********PUB METHODS******** 
	 ***************************/

	public String searchCars() {
		cars = service.getCars(makeId, modelId, styleId, year, price, mileage, bodyType);
		return "/pub/inv/invList";
	}
	
	public String addInventory() {
		return null;
	}
	
	public String submitInventory() {
		return null;
	}
		
}
