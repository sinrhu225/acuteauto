/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.acminds.acuteauto.persistence.dto.Image;
import com.acminds.acuteauto.persistence.dto.Location;
import com.acminds.acuteauto.service.ClientService;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.EnumConstants.LocationType;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ManagedBean(name="cntCtrl")
@ViewScoped
public class ClientController extends BaseController {
	private ClientService service = new ClientService();
	private String activeLocationItem = "item0";
	public String getActiveLocationItem() {
		return activeLocationItem;
	}
	public void setActiveLocationItem(String activeLocationItem) {
		this.activeLocationItem = activeLocationItem;
	}
	
	public void addNewLocation(ActionEvent e) {
		Location l = new Location();
		l.setLocationType(LocationType.SECONDARY);
		l.setCountry(dealer.getPrimaryLocation().getCountry());
		l.setClient(dealer);
		dealer.getLocations().add(l);
		activeLocationItem = "item"+(dealer.getLocations().size()-1);
	}
	
	public void discardLocation(int index) {
		dealer.getLocations().remove(index);
		activeLocationItem = "item"+(dealer.getLocations().size()-1);
	}
	
	public void copyPrimaryLocation(int toIndex) {
		Location from = dealer.getPrimaryLocation();
		Location to = dealer.getLocations().get(toIndex);
		if(Utils.isEmpty(to.getLocationDesc()))
			to.setLocationDesc(from.getLocationDesc());
		if(Utils.isEmpty(to.getAddress1()))
			to.setAddress1(from.getAddress1());
		if(Utils.isEmpty(to.getAddress2()))
			to.setAddress2(from.getAddress2());
		if(Utils.isEmpty(to.getCity()))
			to.setCity(from.getCity());
		if(Utils.isEmpty(to.getState()))
			to.setState(from.getState());
		if(Utils.isEmpty(to.getZip()))
			to.setZip(from.getZip());
		if(Utils.isEmpty(to.getCountry()))
			to.setCountry(from.getCountry());
		if(Utils.isEmpty(to.getBusPhone()))
			to.setBusPhone(from.getBusPhone());
		if(Utils.isEmpty(to.getCellPhone()))
			to.setCellPhone(from.getCellPhone());
		if(Utils.isEmpty(to.getEmail()))
			to.setEmail(from.getEmail());
	}
	
	public void prepareImages(ActionEvent e) {
		List<Image> list = getUploadedImages(WebUtils.getRequest());
		if(!listLoadedAlready(list))
			list.addAll(dealer.getImages());
	}
	
	public String removeLocation(int index) {
		try {
			logger.info("Removing Location at index: "+index);
			Location l = getDealer().getLocations().get(index);
			getDealer().getLocations().remove(index);
			if(l.isPersistent()) {				
				baseService.delete(l, true);
				logger.info("Deleted a saved Location");
			} 
		} catch (Exception e) {
			logger.error("Exception thrown while deleting a location", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return null;
	}
	
	public String submitClientProfile() {
		try {
			List<Image> images = getUploadedImages(WebUtils.getRequest());
			if(!Utils.isEmpty(images) && images.size()>1) {
				WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "multipleLogos");
				return null;
			}
			service.updateClientProfile(images, dealer);
			logger.info("Dealer Profile updated successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "clientUpdated");
		} catch (Exception e) {
			logger.error("Dealer Profile Update failed", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return null;
	}
	
	public String removeImage(int index) {
		logger.info("Removing Image at index: "+index);
		List<Image> list = getUploadedImages(WebUtils.getRequest());
		try {
			Image im = list.get(index);
			if(im.isPersistent()) {
				service.deleteImage(im, dealer, getAuthorizedUser());
				logger.info("Deleted a saved Image");
			}
			list.remove(index);
		} catch(Exception e) {
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}		
		return null;
	}
	
}
