/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.acminds.acuteauto.persistence.dto.Category;
import com.acminds.acuteauto.persistence.dto.Client;
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
	private Category homeGroup;
	private Client dealer;
	private List<Vehicle> carsForBanner;
	private List<Vehicle> featuredCars;
	private List<Vehicle> carsForAdvertisement;
	
	public Client getDealer() {
		if(Utils.isEmpty(dealer))
			dealer = service.getBaseDao().createNamedQuery("getDealer", Client.class).getSingleResult();
		return dealer;
	}
	
	public List<Vehicle> getCarsForBanner() {
		if(Utils.isEmpty(carsForBanner))
			carsForBanner = service.getCarsForBanner();
		return carsForBanner;
	}
	
	public Category getHomeGroup() {
		if(Utils.isEmpty(homeGroup))
			homeGroup = service.getBaseDao().createNamedQuery("getCatByName", Category.class).setParameter("name", "Home Page").getSingleResult();
		return homeGroup;
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
		this.dealer = null;
		this.homeGroup = null;
		this.carsForBanner = null;
		this.featuredCars = null;
		this.carsForAdvertisement = null;
		return null;
	}
}
