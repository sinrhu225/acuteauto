package com.acminds.acuteauto.persistence.dto;

// Generated Feb 29, 2012 11:25:37 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserInfo generated by customhbm2java
 */
import javax.faces.bean.ManagedBean;

import com.acminds.acuteauto.persistence.entities.AbstractUserInfo;

@ManagedBean(name = "userInfo")
@Entity
@Table(name = "USER_INFO", catalog = "carobar", uniqueConstraints = @UniqueConstraint(columnNames = "USER_NAME"))
public class UserInfo extends AbstractUserInfo {
	private static final long serialVersionUID = 1L;

}