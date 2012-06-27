/**
 * 
 */
package com.acminds.acuteauto.service;

import java.util.ArrayList;
import java.util.List;

import com.acminds.acuteauto.persistence.dao.InventoryDAO;
import com.acminds.acuteauto.persistence.dto.Advertisement;
import com.acminds.acuteauto.persistence.dto.Feature;
import com.acminds.acuteauto.persistence.dto.Image;
import com.acminds.acuteauto.persistence.dto.Make;
import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.utils.Constants;
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
	
	public List<Vehicle> getCarsByCategory(int id, String name) {
		return dao.getCarsByCategory(id, name);
	}
	
	public void saveOrUpdateCar(Vehicle car) throws Exception{

		try {
			List<Feature> rem = new ArrayList<Feature>();
			for(Feature p:car.getFeatures()) {
				if(!car.getSelectedFeatures().contains(p)) {
					p.getVehicles().remove(car);
					rem.add(p);						
				}					
			}
			car.getFeatures().removeAll(rem);
			for(Feature p:car.getSelectedFeatures()) {
				if(!car.getFeatures().contains(p)) {
					car.getFeatures().add(p);
					p.getVehicles().add(car);
				}
				saveOrUpdate(p, false);
			}
			for(Image img:car.getImages())
				img.setVehicle(car);
			saveOrUpdate(car, true);
			refresh(car);
			for(Image img:car.getImages()) {
				if(!Utils.isEmpty(img.getImageData())) {
					String location = Utils.getUserHome()+Constants.CAR_IMG_LOC+car.getVehicleId()+"/"+img.getName();
					Utils.writeImage(location, img.getImageData());
					logger.info("Image "+img.getName()+" written to the following location: "+location);		
					img.setImageLocation(Constants.CAR_IMG_LOC+car.getVehicleId()+"/"+img.getName());
				}
				saveOrUpdate(img, false);
			}
			commit();
			logger.info("Vehicle saved successfully.");			
		} catch(Exception e) {
			rollback();
			logger.error("Vehicle could not be saved due to an internal error.", e);	
			throw e;
		}
	}
	
	public void deleteCar() {
		
	}

}
