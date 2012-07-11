/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.acminds.acuteauto.persistence.dto.Feature;
import com.acminds.acuteauto.persistence.dto.FeatureGroup;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ViewScoped
@ManagedBean(name="fetCtrl")
public class FeatureController extends BaseController{

	private Feature feature;
	private Integer groupIndex;
	private FeatureGroup optionsGroup;
	private List<FeatureGroup> managedFeatureGroups;
	
	public Integer getGroupIndex() {
		return groupIndex;
	}
	public void setGroupIndex(Integer groupIndex) {
		this.groupIndex = groupIndex;
	}
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	public FeatureGroup getOptionsGroup() {
		if(Utils.isEmpty(optionsGroup)) {
			optionsGroup = baseService.createNamedQuery("getFeatureGrpByName", FeatureGroup.class).setParameter("name", "OPTIONS").getSingleResult();
		}
		return optionsGroup;
	}
	public List<FeatureGroup> getManagedFeatureGroups() {
		if(managedFeatureGroups==null)
			managedFeatureGroups = new ArrayList<FeatureGroup>(getAllFeatureGroups());
		if(managedFeatureGroups.size()==0 || managedFeatureGroups.get(0).isPersistent()) {
			FeatureGroup s = new FeatureGroup();
			s.setFeatureGroup(getOptionsGroup());
			managedFeatureGroups.add(0, s);
		}
		return managedFeatureGroups;
	}
		
	public String updateFeatureGroup() {
		try {
			logger.info("Saving Feature Group.");
			baseService.saveOrUpdate(optionsGroup, true);
			logger.info("Feature Group saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveFeatSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Feature Group could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return null;
	}
	
	public void submitGroupFromList(Integer groupId) {
		try {
			logger.info("Saving Feature.");
			FeatureGroup fg = getManagedFeatureGroups().get(groupId);
			baseService.saveOrUpdate(fg, true);
			baseService.refresh(fg);
			logger.info("Feature saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveFeatSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Feature could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}		
	}
	
	public String updateFeature() {
		try {
			logger.info("Saving Feature.");
			baseService.saveOrUpdate(feature, true);
			logger.info("Feature saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveFeatSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Feature could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return null;
	}
	
	public void submitFeatureFromList(Integer groupId) {
		try {
			logger.info("Saving Feature.");
			FeatureGroup fg = getManagedFeatureGroups().get(groupId);
			feature = fg.getManagedFeatures().get(0);
			baseService.saveOrUpdate(feature, true);
			fg.getFeatures().add(feature);
			// JUST A WORKAROUND TO RE-RENDER THE NEW-FEATURE COLUMN.
			fg.getManagedFeatures().get(0);
			logger.info("Feature saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "saveFeatSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Feature could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}		
	}
	
	public String deleteFeatureGroup() {
		try {
			logger.info("Deleting Feature Group.");
			FeatureGroup fg = getManagedFeatureGroups().get(groupIndex);
			baseService.delete(fg, true);
			getManagedFeatureGroups().remove(fg);
			logger.info("Feature Group deleted successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delMakeSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Make could not be deleted due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "deleteFailed");
		}
		return null;
	}
	
	public String deleteFeature() {
		try {
			logger.info("Deleting Feature.");
			FeatureGroup fg = getManagedFeatureGroups().get(groupIndex);
			fg.getFeatures().remove(feature);
			fg.getManagedFeatures().remove(feature);
			baseService.delete(feature, true);
			logger.info("Feature deleted successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "delModelSuccessful");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Model could not be deleted due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "deleteFailed");
		}
		return null;
	}
	
}
