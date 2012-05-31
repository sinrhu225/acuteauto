/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.persistence.dto.Category;
import com.acminds.acuteauto.persistence.dto.Vehicle;
import com.acminds.acuteauto.service.InventoryService;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.EnumConstants.CategoryType;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="hmpCtrl")
@SessionScoped
public class HomePageController extends BaseController{	
	private Log logger = LogFactory.getLog(this.getClass());
	private InventoryService service = new InventoryService();
	private List<Category> menuGroup;
	private List<Category> homeGroup;
	private List<Vehicle> carsForBanner;
	
	public String getActiveMenu() {
		String viewId = WebUtils.getFacesContext().getViewRoot().getViewId().replace("xhtml", "jsf");
		for(Category cat:getMenuGroup()) {
			if(!Utils.isEmpty(cat.getMiscData()) && viewId.indexOf(cat.getMiscData())!=-1)
				return cat.getName();
		}
		viewId = viewId.substring(viewId.indexOf("/"), viewId.lastIndexOf("/"));
		for(Category cat:getMenuGroup()) {
			if(!Utils.isEmpty(cat.getMiscData()) && cat.getMiscData().indexOf(viewId)!=-1)
				return cat.getName();
		}
		return null;
	}
	
	public List<Vehicle> getCarsForBanner() {
		if(Utils.isEmpty(carsForBanner))
			carsForBanner = service.getCarsForBanner();
		return carsForBanner;
	}
	
	public List<Category> getMenuGroup() {
		if(Utils.isEmpty(menuGroup))
			menuGroup = baseService.createNamedQuery("getCatsByType", Category.class).setParameter("type", CategoryType.MAIN_MENU).getResultList();
		return menuGroup;
	}
	
	public List<Category> getHomeGroup() {
		if(Utils.isEmpty(homeGroup))
			homeGroup = baseService.createNamedQuery("getCatsByType", Category.class).setParameter("type", CategoryType.HOME_PAGE).getResultList();
		return homeGroup;
	}
	
	public String reset() {
		this.menuGroup = null;
		this.homeGroup = null;
		this.carsForBanner = null;
		return null;
	}
	
	public String saveMenuGroup() {
		try {
			logger.info("Saving Menu Group.");
			baseService.saveOrUpdateAll(menuGroup, false);
			baseService.commit();
			baseService.refreshAll(menuGroup);
			logger.info("Menu saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "menuSaved");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Menu could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return "/sec/adminConsole";
	}
	
	public String saveHomeGroup() {
		try {
			logger.info("Saving Home Group.");
			List<Vehicle> rem = new ArrayList<Vehicle>();
			for(Category cat:homeGroup) {
				for(Vehicle v:cat.getVehicles()) {
					if(!cat.getSelectedVehicles().contains(v)) {
						v.getCategories().remove(cat);
						rem.add(v);						
					}					
				}
				cat.getVehicles().removeAll(rem);
				for(Vehicle v:cat.getSelectedVehicles()) {
					if(!cat.getVehicles().contains(v)) {
						cat.getVehicles().add(v);
						v.getCategories().add(cat);
					}
					baseService.saveOrUpdate(v, false);
				}
				baseService.saveOrUpdate(cat, false);
				rem.clear();
			}
			/*for(Category cat:homeGroup) {
				manageManyToMany(cat, cat.getVehicles(), cat.getSelectedVehicles(), "categories");
				service.saveOrUpdate(cat, false);				
			}*/
			baseService.commit();
			baseService.refreshAll(homeGroup);
			logger.info("Groups saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "groupSaved");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Groups could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return "/sec/adminConsole";
	}
	
}
