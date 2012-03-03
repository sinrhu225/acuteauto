/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.service.InventoryService;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="hmpCtrl", eager=true)
@ApplicationScoped
public class HomePageController extends BaseController{
	private InventoryService service = new InventoryService();
	private List<Vehicle> carsForBanner;
	private List<Vehicle> featuredCars;
	private List<Vehicle> carsForAdvertisement;
	
	public List<Vehicle> getCarsForBanner() {
		if(Utils.isEmpty(carsForBanner))
			carsForBanner = service.getCarsForBanner();
		return carsForBanner;
	}
	
	public List<Vehicle> getFeaturedCars() {
		if(Utils.isEmpty(featuredCars))
			featuredCars = service.getCarsByCategory(0, "FEATURED");
		return featuredCars;
	}
	
	public List<Vehicle> getCarsForAdvertisement() {
		if(Utils.isEmpty(carsForAdvertisement))
			carsForAdvertisement = service.getCarsForAdvertisement();
		return carsForAdvertisement;
	}
	
	public String reset() {
		this.carsForBanner = null;
		this.featuredCars = null;
		this.carsForAdvertisement = null;
		return null;
	}
}
