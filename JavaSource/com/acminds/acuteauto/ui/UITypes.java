/**
 * 
 */
package com.acminds.acuteauto.ui;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.dto.Enum;
import com.acminds.acuteauto.persistence.dto.EnumType;
import com.acminds.acuteauto.utils.EnumConstants;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(eager=true, name="uiTypes")
@ApplicationScoped
public class UITypes {
	private List<SelectItem> userTypes;
	private List<SelectItem> userStatuses;
	private List<SelectItem> locationTypes;
	private List<SelectItem> styleTypes;
	private List<SelectItem> bodyTypes;
	private List<SelectItem> categoryTypes;
	private List<SelectItem> fuelTypes;
	private List<SelectItem> warrantyTypes;
	private List<SelectItem> vehicleStatus;
	private List<SelectItem> vehicleConditions;
	private List<SelectItem> displayTypes;
	private List<SelectItem> imageTypes;
	private List<SelectItem> adStatuses;
	private List<SelectItem> adUnits;
	private List<SelectItem> loanStatuses;
	private List<SelectItem> financeTypes;
	private List<SelectItem> accountTypes;
	private List<SelectItem> inquiryTypes;
	
	/**
	 * @return the userTypes
	 */
	public List<SelectItem> getUserTypes() {
		if(Utils.isEmpty(userTypes)) {
			userTypes = processEnums(EnumConstants.USER_TYPE);			
		}
		return userTypes;
	}
	/**
	 * @return the userStatuses
	 */
	public List<SelectItem> getUserStatuses() {
		if(Utils.isEmpty(userStatuses)) {
			userStatuses = processEnums(EnumConstants.USER_STATUS);			
		}
		return userStatuses;
	}
	/**
	 * @return the locationTypes
	 */
	public List<SelectItem> getLocationTypes() {
		if(Utils.isEmpty(locationTypes)) {
			locationTypes = processEnums(EnumConstants.LOCATION_TYPE);			
		}
		return locationTypes;
	}
	/**
	 * @return the styleTypes
	 */
	public List<SelectItem> getStyleTypes() {
		if(Utils.isEmpty(styleTypes)) {
			styleTypes = processEnums(EnumConstants.STYLE_TYPE);			
		}
		return styleTypes;
	}
	/**
	 * @return the bodyTypes
	 */
	public List<SelectItem> getBodyTypes() {
		if(Utils.isEmpty(bodyTypes)) {
			bodyTypes = processEnums(EnumConstants.BODY_TYPE);			
		}
		return bodyTypes;
	}
	/**
	 * @return the categoryTypes
	 */
	public List<SelectItem> getCategoryTypes() {
		if(Utils.isEmpty(categoryTypes)) {
			categoryTypes = processEnums(EnumConstants.CATEGORY_TYPE);			
		}
		return categoryTypes;
	}
	/**
	 * @return the fuelTypes
	 */
	public List<SelectItem> getFuelTypes() {
		if(Utils.isEmpty(fuelTypes)) {
			fuelTypes = processEnums(EnumConstants.FUEL_TYPE);			
		}
		return fuelTypes;
	}
	/**
	 * @return the warrantyTypes
	 */
	public List<SelectItem> getWarrantyTypes() {
		if(Utils.isEmpty(warrantyTypes)) {
			warrantyTypes = processEnums(EnumConstants.WARRANTY_TYPE);			
		}
		return warrantyTypes;
	}
	/**
	 * @return the vehicleStatus
	 */
	public List<SelectItem> getVehicleStatus() {
		if(Utils.isEmpty(vehicleStatus)) {
			vehicleStatus = processEnums(EnumConstants.VEHICLE_STATUS);			
		}
		return vehicleStatus;
	}
	/**
	 * @return the vehicleConditionTypes
	 */
	public List<SelectItem> getVehicleConditions() {
		if(Utils.isEmpty(vehicleConditions)) {
			vehicleConditions = processEnums(EnumConstants.VEHICLE_CONDITION);			
		}
		return vehicleConditions;
	}
	/**
	 * @return the displayTypes
	 */
	public List<SelectItem> getDisplayTypes() {
		if(Utils.isEmpty(displayTypes)) {
			displayTypes = processEnums(EnumConstants.DISPLAY_TYPE);			
		}
		return displayTypes;
	}
	/**
	 * @return the imageTypes
	 */
	public List<SelectItem> getImageTypes() {
		if(Utils.isEmpty(imageTypes)) {
			imageTypes = processEnums(EnumConstants.IMAGE_TYPE);			
		}
		return imageTypes;
	}
	/**
	 * @return the adStatuses
	 */
	public List<SelectItem> getAdStatuses() {
		if(Utils.isEmpty(adStatuses)) {
			adStatuses = processEnums(EnumConstants.AD_STATUS);			
		}
		return adStatuses;
	}
	/**
	 * @return the adUnits
	 */
	public List<SelectItem> getAdUnits() {
		if(Utils.isEmpty(adUnits)) {
			adUnits = processEnums(EnumConstants.AD_UNITS);			
		}
		return adUnits;
	}
	/**
	 * @return the loanStatuses
	 */
	public List<SelectItem> getLoanStatuses() {
		if(Utils.isEmpty(loanStatuses)) {
			loanStatuses = processEnums(EnumConstants.LOAN_STATUS);			
		}
		return loanStatuses;
	}
	/**
	 * @return the financeTypes
	 */
	public List<SelectItem> getFinanceTypes() {
		if(Utils.isEmpty(financeTypes)) {
			financeTypes = processEnums(EnumConstants.FINANCE_TYPE);			
		}
		return financeTypes;
	}
	/**
	 * @return the accountTypes
	 */
	public List<SelectItem> getAccountTypes() {
		if(Utils.isEmpty(accountTypes)) {
			accountTypes = processEnums(EnumConstants.ACCOUNT_TYPE);			
		}
		return accountTypes;
	}
	/**
	 * @return the inquiryTypes
	 */
	public List<SelectItem> getInquiryTypes() {
		if(Utils.isEmpty(inquiryTypes)) {
			inquiryTypes = processEnums(EnumConstants.INQUIRY_TYPE);			
		}
		return inquiryTypes;
	}
	
	private List<SelectItem> processEnums(int enumType) {
		BaseDAO dao = new BaseDAO();
		List<Enum> enums = dao.get(EnumType.class, enumType).getEnums();
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(WebUtils.getDefaultSelectItem(false, null));
		for(Enum e: enums) {
			list.add(new SelectItem(e.getEnumId(), e.getShortDesc()));
		}
		return list;
	}
	
	
}
