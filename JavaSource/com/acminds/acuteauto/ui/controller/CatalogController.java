/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import com.acminds.acuteauto.persistence.dto.Make;
import com.acminds.acuteauto.persistence.dto.Model;
import com.acminds.acuteauto.persistence.dto.Style;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="ctlCtrl")
@ViewScoped
public class CatalogController extends BaseController {

	private Make make;
	private Model model;
	private Style style;
	/**
	 * @return the make
	 */
	public Make getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(Make make) {
		this.make = make;
	}
	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	/**
	 * @return the style
	 */
	public Style getStyle() {
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(Style style) {
		this.style = style;
	}
	
	public void loadMake(ComponentSystemEvent e) {
		if(make == null) {
			make = new Make();
		}
	}
	
	public void loadModel(ComponentSystemEvent e) {
		if(model == null) {
			model = new Model();
			if(make!=null)
				model.setMake(make);
		}
	}
	
	public void loadStyle(ComponentSystemEvent e) {
		if(style == null) {
			style = new Style();
			if(make!=null)
				model.setMake(make);
			if(model!=null)
				style.setModel(model);			
		}
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
	
	
}
