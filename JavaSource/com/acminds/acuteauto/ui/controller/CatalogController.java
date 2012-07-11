/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.acminds.acuteauto.persistence.dto.Make;
import com.acminds.acuteauto.persistence.dto.Model;
import com.acminds.acuteauto.persistence.dto.Style;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="ctlCtrl")
@ViewScoped
public class CatalogController extends InventoryController {

	private Make make;
	private Model model;
	private Style style;
	private List<Make> makesForCatList;
	
	public Make getMake() {
		return make;
	}
	public void setMake(Make make) {
		this.make = make;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	public List<Make> getMakesForCatList(){
		if(makesForCatList==null)
			makesForCatList = new ArrayList<Make>(getAllMakes());
		if(makesForCatList.get(0).isPersistent())
			makesForCatList.add(0, new Make());
		return makesForCatList;
	}
	
	public String prepareCatalog() {
		this.make = new Make();
		this.model = new Model();
		this.style = new Style();
		return null;
	}
	
	public void reset() {
		this.make = null;
		this.model = null;
		this.style = null;		
	}
	
	public String submitMake() {
		try {
			logger.info("Saving Make.");
			baseService.saveOrUpdate(make, true);
			logger.info("Make saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveMakeSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Make could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return null;
	}
	
	public void submitMake(AjaxBehaviorEvent abe) {
		try {
			logger.info("Saving Make.");
			baseService.saveOrUpdate(make, true);
			if(!make.isPersistent()) {
				baseService.refresh(make);
				getAllMakes().add(make);
				// TO PRESELECT MAKE WHILE CREATING NEW MODEL 
				model.setMake(make);
			} else {
				make.setEditable(false);
			}
			logger.info("Make saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveMakeSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Make could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}		
	}
	
	public void submitMakeFromList(AjaxBehaviorEvent abe) {
		try {
			logger.info("Saving Make.");
			this.make = getMakesForCatList().get(0);
			baseService.saveOrUpdate(make, true);
			baseService.refresh(make);
			setModel(null);
			setStyle(null);
			logger.info("Make saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveMakeSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Make could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}		
	}
	
	public String submitModel() {
		try {
			logger.info("Saving Model.");
			baseService.saveOrUpdate(model, true);
			logger.info("Model saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveModelSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Model could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return null;
	}
	
	public void submitModel(AjaxBehaviorEvent abe) {
		try {
			logger.info("Saving Model.");
			baseService.saveOrUpdate(model, true);
			if(!model.isPersistent()) {
				baseService.refresh(model);
				// TO PRESELECT MODEL WHILE CREATING NEW STYLE
				style.setModel(model);
				style.setMake(model.getMake());
			}
			logger.info("Model saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveModelSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Model could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
	}
	
	public void submitModelFromList(AjaxBehaviorEvent abe) {
		try {
			logger.info("Saving Model.");
			model = make.getModelsForCatList().get(0);
			baseService.saveOrUpdate(model, true);
			baseService.refresh(model);
			make.getModels().add(model);
			setStyle(null);
			logger.info("Model saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveModelSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Model could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
	}
	
	public String submitStyle() {
		try {
			logger.info("Saving Style.");
			baseService.saveOrUpdate(style, true);
			logger.info("Style saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveStyleSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Style could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return null;
	}
	
	public void submitStyle(AjaxBehaviorEvent abe) {
		try {
			logger.info("Saving Style.");
			baseService.saveOrUpdate(style, true);
			if(!style.isPersistent()) {
				baseService.refresh(style);
			}
			logger.info("Style saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveStyleSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Style could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
	}
	
	public void submitStyleFromList(AjaxBehaviorEvent abe) {
		try {
			logger.info("Saving Style.");
			style = model.getStylesForCatList().get(0);
			baseService.saveOrUpdate(style, true);
			baseService.refresh(style);
			model.getStyles().add(style);
			logger.info("Style saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveStyleSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Style could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
	}
	
	public String deleteMake() {
		try {
			logger.info("Deleting Make.");
			baseService.delete(make, true);
			getMakesForCatList().remove(make);
			reset();
			logger.info("Make deleted successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delMakeSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Make could not be deleted due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "deleteFailed");
		}
		return null;
	}
	
	public String deleteModel() {
		try {
			logger.info("Deleting Model.");
			make.getModels().remove(model);
			make.getModelsForCatList().remove(model);
			baseService.delete(model, true);
			setModel(null);
			setStyle(null);
			logger.info("Model deleted successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delModelSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Model could not be deleted due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "deleteFailed");
		}
		return null;
	}
	
	public String deleteStyle() {
		try {
			logger.info("Deleting Style.");
			model.getStyles().remove(style);
			model.getStylesForCatList().remove(style);
			baseService.delete(style, true);
			setStyle(null);
			logger.info("Style deleted successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delStyleSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Style could not be deleted due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "deleteFailed");
		}
		return null;
	}
	
	
}
