package com.acminds.acuteauto.persistence.dto;

// Generated Feb 29, 2012 11:25:37 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.acminds.acuteauto.persistence.entities.AbstractVehicle;
import com.acminds.acuteauto.utils.EnumConstants.ImageType;
import com.acminds.acuteauto.utils.Utils;

@ManagedBean(name = "vehicle")
@Entity
@Table(name = "VEHICLE")
public class Vehicle extends AbstractVehicle {
	private static final long serialVersionUID = 1L;
	@Transient
	public Image getBannerImage() {
		for(Image im: getImages()) {
			if(im.getBanner()!=null && im.getBanner())
				return im;
		}
		return null;
	}
	
	private Image displayImage; 
	@Transient
	public Image getDisplayImage() {
		if(displayImage == null) {
			for(Image im: getImages()) {
				if(!Utils.isEmpty(im.getImageType()) && im.getImageType() == ImageType.PRIMARY)
					displayImage = im;
			}
		}
		return displayImage;
	}
	
	private List<FeatureGroup> distinctGroups; 
	@Transient
	public List<FeatureGroup> getDistinctGroups() {
		if(distinctGroups == null) {
			distinctGroups = new ArrayList<FeatureGroup>();
			for(Feature f:getFeatures()) {
				if(!distinctGroups.contains(f.getFeatureGroup()))
					distinctGroups.add(f.getFeatureGroup());
			}
		}
		return distinctGroups;
	}
	
	private List<Feature> selectedFeatures;
	@Transient
	public List<Feature> getSelectedFeatures() {
		return selectedFeatures;
	}
	public void setSelectedFeatures(List<Feature> selectedFeatures) {
		this.selectedFeatures = selectedFeatures;
	}	
	
}
