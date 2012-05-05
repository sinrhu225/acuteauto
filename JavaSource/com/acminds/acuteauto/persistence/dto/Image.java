package com.acminds.acuteauto.persistence.dto;

// Generated Feb 29, 2012 11:25:37 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Image generated by customhbm2java
 */
import javax.faces.bean.ManagedBean;

import com.acminds.acuteauto.persistence.entities.AbstractImage;
import com.acminds.acuteauto.utils.Utils;

@ManagedBean(name = "image")
@Entity
@Table(name = "IMAGE")
public class Image extends AbstractImage {
	private static final long serialVersionUID = 1L;
	@Transient
	public String getRealLocation() {
		return Utils.getUserHome()+getImageLocation();
	}

}
