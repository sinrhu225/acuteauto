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

/**
 * @author Mansur
 *
 */
@ManagedBean(name="hmpCtrl", eager=true)
@ApplicationScoped
public class HomePageController extends BaseController{
	private InventoryService service = new InventoryService();
	
	public List<Vehicle> getCarsForBanner() {
		return service.getCarsForBanner();
	}
	
	public List<Vehicle> getFeaturedCars() {
		return service.getCarsByCategory(0, "FEATURED");
	}
	
	public List<Vehicle> getCarsForAdvertisement() {
		return service.getCarsForAdvertisement();
	}
	
}
