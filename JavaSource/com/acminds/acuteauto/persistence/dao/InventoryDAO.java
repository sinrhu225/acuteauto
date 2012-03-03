/**
 * 
 */
package com.acminds.acuteauto.persistence.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.dto.Advertisement;
import com.acminds.acuteauto.persistence.dto.Category;
import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
public class InventoryDAO extends BaseDAO {
	
	public List<Vehicle> getCars(String makeId, String modelId, String styleId, String year, String price, String mileage, String bodyType) {
		String q = "from Vehicle v where 1=1 ";
		if(!Utils.isEmpty(makeId)) q= q+" and v.make.id = "+makeId;
		if(!Utils.isEmpty(modelId)) q= q+" and v.model.id = "+modelId;
		if(!Utils.isEmpty(styleId)) q= q+" and v.style.id = "+styleId;
		if(!Utils.isEmpty(year)) q= q+" and v.year = "+year;
		if(!Utils.isEmpty(bodyType)) q= q+" and v.style.vehicleType= "+bodyType;
		if(!Utils.isEmpty(price)) q= q+" and v.salePrice <= "+price;
		if(!Utils.isEmpty(mileage)) q= q+" and v.mileage <= "+mileage;		
		TypedQuery<Vehicle> tq = createQuery(q, Vehicle.class);
		return tq.getResultList();		
	}
	
	public List<Vehicle> getCarsByCategory(int categoryId, String categoryName) {
		Category c = null;
		if(categoryId>0) {
			c = get(Category.class, categoryId);			
		} else if (!Utils.isEmpty(categoryName)) {
			String q = "from Category c where c.name="+categoryName;
			TypedQuery<Category> tq = createQuery(q, Category.class);
			c = tq.getSingleResult();
		}
		if(!Utils.isEmpty(c))
			return c.getVehicles();
		return null;		
	}
	
	public List<Advertisement> getAdvertisements() {
		String q = "from Advertisement a where a.effectiveDate<= :today and a.expiryDate>= :today";
		List<Advertisement> list = createQuery(q, Advertisement.class)
									.setParameter("today", Utils.today())
									.getResultList();
		return list;
	}

}
