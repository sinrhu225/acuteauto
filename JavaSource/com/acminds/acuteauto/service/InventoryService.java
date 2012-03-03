/**
 * 
 */
package com.acminds.acuteauto.service;

import java.util.ArrayList;
import java.util.List;

import com.acminds.acuteauto.persistence.dao.InventoryDAO;
import com.acminds.acuteauto.persistence.dto.Advertisement;
import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.utils.Utils;


/**
 * @author Mansur
 *
 */
public class InventoryService {
	private InventoryDAO dao = new InventoryDAO();
	
	public List<Vehicle> getCars(String makeId, String modelId, String styleId, String year, String price, String mileage, String bodyType) {
		return dao.getCars(makeId, modelId, styleId, year, price, mileage, bodyType);
	}
	
	public List<Vehicle> getCarsByCategory(int id, String name) {
		return dao.getCarsByCategory(id, name);
	}
	
	public List<Vehicle> getCarsForBanner() {
		List<Vehicle> cars = new ArrayList<Vehicle>();
		List<Vehicle> list = getCarsForAdvertisement();
		for(Vehicle v: list) {
			if(!Utils.isEmpty(v.getBannerLocation()))
				cars.add(v);					
		}
		return cars;
	}
	
	public List<Vehicle> getCarsForAdvertisement() {
		List<Vehicle> cars = new ArrayList<Vehicle>();
		List<Advertisement> list = dao.getAdvertisements();
		for(Advertisement a:list)
			cars.add(a.getVehicle());
		return cars;
	}

}
