/**
 * 
 */
package com.acminds.acuteauto.service;

import java.util.ArrayList;
import java.util.List;

import com.acminds.acuteauto.persistence.dao.InventoryDAO;
import com.acminds.acuteauto.persistence.dto.Advertisement;
import com.acminds.acuteauto.persistence.dto.Make;
import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.utils.Utils;


/**
 * @author Mansur
 *
 */
public class InventoryService extends BaseService {
	private InventoryDAO dao = new InventoryDAO();
	public InventoryDAO getDao() {
		return dao;
	}
	
	public List<Make> getMakes(int year) {
		return dao.getMakes(year);
	}
	
	public List<Vehicle> getCars(int makeId, int modelId, int styleId, int year, int price, int mileage, int bodyType) {
		return dao.getCars(makeId, modelId, styleId, year, price, mileage, bodyType);
	}
	
	public List<Vehicle> getCarsByCategory(int id, String name) {
		return dao.getCarsByCategory(id, name);
	}
	
	public List<Vehicle> getCarsForBanner() {
		List<Vehicle> cars = new ArrayList<Vehicle>();
		List<Vehicle> list = getCarsForAdvertisement();
		for(Vehicle v: list) {
			if(!Utils.isEmpty(v.getBannerImage()))
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
	
	public void saveOrUpdateCar() {
		
	}
	
	public void saveOrUpdateCarImages() {
		
	}
	
	public void deleteCar() {
		
	}

}
