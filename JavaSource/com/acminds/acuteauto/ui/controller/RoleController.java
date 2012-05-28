/**
 * 
 */
package com.acminds.acuteauto.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.acminds.acuteauto.persistence.dto.Privilege;
import com.acminds.acuteauto.persistence.dto.Role;
import com.acminds.acuteauto.ui.BaseController;
import com.acminds.acuteauto.utils.Utils;
import com.acminds.acuteauto.utils.WebUtils;

/**
 * @author Mansur
 *
 */
@ViewScoped
@ManagedBean(name="rlCtrl")
public class RoleController extends BaseController {
	
	private List<Role> roles;
	private List<Privilege> privs;
	private Role role;
	private Privilege priv;
	
	public List<Role> getRoles() {
		if(Utils.isEmpty(roles))
			roles = baseService.createNamedQuery("getRoles", Role.class).getResultList();
		return roles;
	}
	public List<Privilege> getPrivs() {
		if(Utils.isEmpty(privs))
			privs = baseService.createNamedQuery("getPrivileges", Privilege.class).getResultList();
		return privs;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Privilege getPriv() {
		return priv;
	}
	public void setPriv(Privilege priv) {
		this.priv = priv;
	}
	
	public String newPrivilege() {
		priv = new Privilege();
		return "sec/rom/privEdit";
	}
	
	public String submitPrivilege() {
		try {
			logger.info("Saving Privilege.");
			baseService.saveOrUpdate(priv, true);
			logger.info("Privilege saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "Privilege saved successfully.");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Privilege could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return "sec/rom/privList";
	}
	
	public String newRole() {
		role = new Role();
		return "sec/rom/roleEdit";
	}
	
	public String submitRole() {
		try {
			logger.info("Saving Role.");
			List<Privilege> rem = new ArrayList<Privilege>();
			
			for(Privilege p:role.getPrivileges()) {
				if(!role.getSelectedPrivs().contains(p)) {
					p.getRoles().remove(role);
					rem.add(p);						
				}					
			}
			role.getPrivileges().removeAll(rem);
			for(Privilege p:role.getSelectedPrivs()) {
				if(!role.getPrivileges().contains(p)) {
					role.getPrivileges().add(p);
					p.getRoles().add(role);
				}
				baseService.saveOrUpdate(p, false);
			}
			baseService.saveOrUpdate(role, false);
			baseService.commit();
			baseService.refresh(role);
			logger.info("Role saved successfully.");
			WebUtils.addMessage(FacesMessage.SEVERITY_INFO, "Role saved successfully.");
		} catch(Exception e) {
			baseService.rollback();
			logger.error("Role could not be saved due to an internal error.", e);
			WebUtils.addMessage(FacesMessage.SEVERITY_ERROR, "submitFailed");
		}
		return "sec/rom/roleList";
	}

}
