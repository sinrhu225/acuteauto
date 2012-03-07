/**
 * 
 */
package com.acminds.acuteauto.persistence.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.dto.Advertisement;
import com.acminds.acuteauto.persistence.dto.Category;
import com.acminds.acuteauto.persistence.dto.Make;
import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.utils.EnumConstants.AdStatus;
import com.acminds.acuteauto.utils.Utils;

/**
 * @author Mansur
 *
 */
public class InventoryDAO extends BaseDAO {
	
	public List<Vehicle> getCars(int makeId, int modelId, int styleId, int year, int price, int mileage, int bodyType) {
		String q = "from Vehicle v where 1=1 ";
		if(makeId>0) q= q+" and v.make.id = "+makeId;
		if(modelId>0) q= q+" and v.model.id = "+modelId;
		if(styleId>0) q= q+" and v.style.id = "+styleId;
		if(year>0) q= q+" and v.year = "+year;
		if(bodyType>0) q= q+" and v.style.vehicleType= "+bodyType;
		if(price>0) q= q+" and v.salePrice <= "+price;
		if(mileage>0) q= q+" and v.mileage <= "+mileage;		
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
		String q = "from Advertisement a where a.effectiveDate<= :today and a.status= :stat";
		List<Advertisement> list = createQuery(q, Advertisement.class)
									.setParameter("today", Utils.today())
									.setParameter("stat", AdStatus.ACTIVE)
									.getResultList();
		return list;
	}
	
	public List<Make> getMakes(int year) {
		String q = "from Make m where 1=1 ";
		if(year>0)
			q = q+"and m.yearStart<= "+year+" and m.yearEnd>= "+year;
		List<Make> list = createQuery(q, Make.class)
									.getResultList();
		return list;
	}

}
