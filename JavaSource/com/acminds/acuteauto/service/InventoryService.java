/**
 * 
 */
package com.acminds.acuteauto.service;

import java.util.List;

import com.acminds.acuteauto.persistence.dao.InventoryDAO;
import com.acminds.acuteauto.persistence.dto.Make;
import com.acminds.acuteauto.persistence.dto.Vehicle;


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
	
	public void saveOrUpdateCar() {
		
	}
	
	public void saveOrUpdateCarImages() {
		
	}
	
	public void deleteCar() {
		
	}

}
